package cn.t.util.io;

import cn.t.util.common.ArrayUtil;
import cn.t.util.common.CollectionUtil;
import cn.t.util.common.RandomUtil;
import cn.t.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * path 不以'/'开头时默认是从此类所在的包下取资源，以'/'开头则是从ClassPath(Src根目录)根下获取。
     * 其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
     * @param clazz xxx
     * @param path xxx
     * @return xxx
     */
    public static InputStream getResourceInputStream(Class<?> clazz, String path) {
        logger.debug("load input stream by class {}, path: {}", clazz.getName(), path);
        return clazz.getResourceAsStream(path);
    }

    /**
     * 默认则是从ClassPath根下获取，path不能以'/'开头，最终是由ClassLoader获取资源。
     * @param classLoader xxx
     * @param path xxx
     * @return xxx
     */
    public static InputStream getResourceInputStream(ClassLoader classLoader, String path) {
        logger.debug("load input stream by classloader {}, path: {}", classLoader, path);
        return classLoader.getResourceAsStream(path);
    }

    public static FileChannel getFileChannel(String filePath, OpenOption... openOption) throws IOException {
        URI uri = URI.create("file:///" + filePath);
        Path path = Paths.get(uri);
        return FileChannel.open(path, openOption);
    }

    public static BufferedReader getMappedBufferedReader(String filePath) throws IOException {
        FileChannel fileChannel = getFileChannel(filePath, StandardOpenOption.READ);
        return new BufferedReader(Channels.newReader(fileChannel, StandardCharsets.UTF_8.name()));
    }

    public static FileInputStream getResourceFileInputStream(ClassLoader classLoader, String path) throws URISyntaxException, FileNotFoundException {
        logger.debug("load input stream by classloader {}, path: {}", classLoader, path);
        URL url = classLoader.getResource(path);
        if (url != null) {
            return new FileInputStream(new File(url.toURI()));
        }
        return null;
    }

    public static FileInputStream getFileInputStream(String path) throws FileNotFoundException {
        logger.debug("load input stream by file path {}", path);
        return new FileInputStream(path);
    }

    public static DataInputStream getResourceDataInputStream(String path) throws FileNotFoundException {
        logger.debug("load input stream by file path {}", path);
        return new DataInputStream(getFileInputStream(path));
    }

    public static FileReader getFileReader(String path) throws FileNotFoundException {
        logger.debug("load input stream by file path {}", path);
        return new FileReader(path);
    }

    public static BufferedInputStream getBufferedInputStream(String path) throws FileNotFoundException {
        return new BufferedInputStream(getFileInputStream(path));
    }

    public static BufferedReader getBufferedReader(String path) throws FileNotFoundException {
        return new BufferedReader(getFileReader(path));
    }

    public static byte[] getFileBytes(String path) throws IOException {
        try (BufferedInputStream bis = getBufferedInputStream(path)) {
            byte[] content = new byte[bis.available()];
            int length = bis.read(content);
            logger.debug("read file length: {}", length);
            return content;
        }
    }

    public static String getProjectClassPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public static String saveToTempDir(byte[] bytes, String contentType) throws IOException {
        String path = appendFilePath(System.getProperty("java.io.tmpdir"), String.valueOf(System.currentTimeMillis()).concat(String.valueOf(System.currentTimeMillis()).concat(RandomUtil.randomString(5))).concat(".").concat(analyseImageTail(contentType)));
        File file = new File(path);
        boolean success = file.createNewFile();
        if (!success) {
            logger.error("cannot create new file at: {}", System.getProperty("java.io.tmpdir"));
            return null;
        }
        try (
            FileOutputStream fos = new FileOutputStream(file)
        ) {
            fos.write(bytes);
        }
        return path;
    }

    public static void saveFileTo(byte[] bytes, String fileTo) throws IOException {
        File file = new File(fileTo);
        if(!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                throw new RuntimeException(String.format("cannot create new file at: %s", fileTo));
            }
        }
        try (
            FileOutputStream fos = new FileOutputStream(file)
        ) {
            fos.write(bytes);
        }
    }

    private static String analyseImageTail(String contentType) {
        String[] eles = contentType.split("/");
        return eles.length > 1 ? eles[1] : eles[0];
    }


    /**
     * 文件 zero copy 复制
     * @param from xxx
     * @param to xxx
     * @throws IOException xxx
     */
    public static void copyFile(String from, String to) throws IOException {
        try (
            FileInputStream fis = new FileInputStream(from);
            FileOutputStream fos = new FileOutputStream(to);
            FileChannel fci = fis.getChannel();
            FileChannel fco = fos.getChannel()
        ) {
            fci.transferTo(0, fci.size(), fco);
        }
    }

    public static void copyFile(InputStream from, String to) throws IOException {
        int len;
        byte[] cache = new byte[2048];
        try (
            FileOutputStream fos = new FileOutputStream(to);
            InputStream is = from
        ) {
            while ((len = is.read(cache)) > 1) {
                fos.write(cache, 0, len);
            }
        }
    }

    /**
     * 获取文件mime type
     * @param path xxx
     * @return xxx
     * @throws IOException xxx
     */
    public static String getMimeType(String path) throws IOException {

        try (
            BufferedInputStream bis = getBufferedInputStream(path)
        ) {
            String mineType = URLConnection.guessContentTypeFromStream(bis);
            if (mineType == null) {
                return "application/octet-stream";
            } else {
                return mineType;
            }
        }
    }

    /**
     * 创建临时文件
     * @param prefix xxx
     * @param suffix xxx
     * @return xxx
     * @throws IOException xxx
     */
    public static Path createTempFile(String prefix, String suffix) throws IOException {
        return Files.createTempFile(prefix, suffix);
    }

    /**
     * 将输入流写入文件
     * @param in     xxx
     * @param prefix xxx
     * @param suffix xxx
     * @return xxx
     * @throws IOException xxx
     */
    public static Path copyInputStreamToTempFile(InputStream in, String prefix, String suffix) throws IOException {
        Path tmp = createTempFile(prefix, suffix);
        Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING);
        return tmp;
    }

    public static String appendFilePath(String original, String append) {
        if(original.endsWith(File.separator)) {
            return original + append;
        } else {
            return original + File.separator + append;
        }
    }

    public static boolean initFile(String file) throws IOException {
        return initFile(new File(file));
    }

    public static boolean initFile(File file) throws IOException {
        if(!file.exists()) {
            if(!initDirectory(file.getParentFile())) {
                return false;
            }
            return file.createNewFile();
        }
        return true;
    }

    public static boolean initDirectory(File file) {
        if(!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static void zip(File outputFile, String... paths) throws IOException {
        if(initFile(outputFile)) {
            try (
                OutputStream os = new FileOutputStream(outputFile)
            ){
                zip(os, paths);
            }
        }
    }

    public static void zip(File outputFile, Map<String, List<String>> pathMappings) throws IOException {
        if(initFile(outputFile)) {
            try (
                OutputStream os = new FileOutputStream(outputFile)
            ){
                zip(os, pathMappings);
            }
        }
    }

    public static void zipWithRename(File outputFile, Map<String, List<Map.Entry<String, String>>> pathMappings) throws IOException {
        if(initFile(outputFile)) {
            try (
                OutputStream os = new FileOutputStream(outputFile)
            ){
                zipWithRename(os, pathMappings);
            }
        }
    }

    public static void zip(OutputStream outputStream, Map<String, List<String>> pathMappings) throws IOException {
        if(!CollectionUtil.isEmpty(pathMappings)) {
            try(
                ZipOutputStream zipOut = new ZipOutputStream(outputStream)
            ) {
                for(Map.Entry<String, List<String>> pathMapping: pathMappings.entrySet()) {
                    String parent;
                    if("default".equals(pathMapping.getKey())) {
                        parent = null;
                    } else {
                        parent = pathMapping.getKey();
                    }
                    List<String> pathList = pathMapping.getValue();
                    if(!CollectionUtil.isEmpty(pathList)) {
                        for(String path: pathList) {
                            File inputFile = new File(path);
                            if(inputFile.exists() && (inputFile.isFile() || inputFile.isDirectory())) {
                                doZip(parent, null, inputFile, zipOut);
                            } else {
                                throw new RuntimeException("what fuck is the file type");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void zipWithRename(OutputStream outputStream, Map<String, List<Map.Entry<String, String>>> pathMappings) throws IOException {
        if(!CollectionUtil.isEmpty(pathMappings)) {
            try(
                ZipOutputStream zipOut = new ZipOutputStream(outputStream)
            ) {
                for(Map.Entry<String, List<Map.Entry<String, String>>> pathMapping: pathMappings.entrySet()) {
                    String parent;
                    if("default".equals(pathMapping.getKey())) {
                        parent = null;
                    } else {
                        parent = pathMapping.getKey();
                    }
                    List<Map.Entry<String, String>> pathList = pathMapping.getValue();
                    if(!CollectionUtil.isEmpty(pathList)) {
                        for(Map.Entry<String, String> pathEntry: pathList) {
                            File inputFile = new File(pathEntry.getKey());
                            if(inputFile.exists() && (inputFile.isFile() || inputFile.isDirectory())) {
                                doZip(parent, pathEntry.getValue(), inputFile, zipOut);
                            } else {
                                throw new RuntimeException("what fuck is the file type");
                            }
                        }
                    }
                }
            }
        }
    }


    public static void zip(OutputStream outputStream, String... paths) throws IOException {
        if(!ArrayUtil.isEmpty(paths)) {
            try(
                ZipOutputStream zipOut = new ZipOutputStream(outputStream)
            ) {
                for(String path: paths) {
                    File inputFile = new File(path);
                    if(inputFile.exists() && (inputFile.isFile() || inputFile.isDirectory())) {
                        doZip(null, null, inputFile, zipOut);
                    } else {
                        throw new RuntimeException("what fuck is the file type");
                    }
                }
            }
        }
    }

    private static void doZip(String parent, String entryTailName, File inputFile, ZipOutputStream zipOutputStream) throws IOException {
        String entryName = entryTailName;
        if(StringUtil.isEmpty(entryName)) {
            entryName = inputFile.getName();
        }
        if(!StringUtil.isEmpty(parent)) {
            entryName = parent.concat(File.separator).concat(entryName);
        }
        if(inputFile.isDirectory()) {
            if(inputFile.getName().endsWith(File.separator)) {
                zipOutputStream.putNextEntry(new ZipEntry(entryName));
            } else {
                zipOutputStream.putNextEntry(new ZipEntry(entryName + File.separator));
            }
            zipOutputStream.closeEntry();
            File[] children = inputFile.listFiles();
            if(!ArrayUtil.isEmpty(children)) {
                for(File childFile: children) {
                    doZip(entryName, null, childFile, zipOutputStream);
                }
            }
        } else {
            zipFile(entryName, inputFile, zipOutputStream);
        }
    }

    private static void zipFile(String entryName, File inputFile, ZipOutputStream zipOutputStream) throws IOException {
        try (
            FileInputStream fis = new FileInputStream(inputFile)
        ){
            ZipEntry zipEntry = new ZipEntry(StringUtil.isEmpty(entryName) ? inputFile.getName(): entryName);
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOutputStream.write(bytes, 0, length);
            }
            zipOutputStream.closeEntry();
        }
    }

    public static void unzip(String filePath, String target) throws IOException {
        unzip(new File(filePath), new File(target));
    }
    public static void unzip(File inputFile, File outputDirectory) throws IOException {
        if(!inputFile.exists()) {
            return;
        }
        initDirectory(outputDirectory);
        try (
            ZipInputStream zis = new ZipInputStream(new FileInputStream(inputFile))
        ){
            ZipEntry zipEntry = zis.getNextEntry();
            byte[] buffer = new byte[1024];
            while (zipEntry != null) {
                if(zipEntry.getName().endsWith(File.separator)) {
                    initDirectory(new File(outputDirectory.getCanonicalPath().concat(File.separator).concat(zipEntry.getName())));
                } else {
                    File newFile = new File(outputDirectory, zipEntry.getName());
                    if(!newFile.getCanonicalPath().startsWith(outputDirectory.getCanonicalPath() + File.separator)) {
                        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
                    }
                    try(
                        FileOutputStream fos = new FileOutputStream(newFile)
                    ) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        }
    }

    public static URI createFileUri(String path) {
        if(StringUtil.isEmpty(path)) {
            throw new IllegalArgumentException("path cannot be empty");
        }
        StringBuilder builder = new StringBuilder();
        if(!path.startsWith("file://")) {
            builder.append("file://");
            if(!path.startsWith("/")) {
                builder.append("/");
            }
        }
        builder.append(path);
        return URI.create(builder.toString());
    }

    public static String extractFileExtension(String fileName) {
        if(StringUtil.isEmpty(fileName)) {
            return "";
        }
        int dotIndex = fileName.indexOf('.');
        if(dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }
}
