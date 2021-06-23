package cn.t.util.io.test;

import cn.t.util.common.JsonUtil;
import cn.t.util.common.digital.HexUtil;
import cn.t.util.io.FileUtil;
import cn.t.util.security.message.base64.Base64Util;
import cn.t.util.security.message.encryption.aes.AesUtil;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

public class FileUtilTest {

    public static String decode(String voteParam) throws Exception {
        /**  密钥  **/
        String key = "wxsk123654789000";
        byte[] original = AesUtil.decrypt(Base64Util.decode(voteParam.getBytes()), key.getBytes(), 128);
        String[] split = new String(original).split("\\$");
        return split[0];
    }

    @Test
    public void copyFileTest() throws IOException {
        String from = "/home/amen/Desktop/logo.jpg";
        String to = "/home/amen/Desktop/logo-bak.jpg";
        FileUtil.copyFile(from, to);
    }

    @Test
    public void getMimeTypeTest() throws IOException {
        System.out.println(FileUtil.getMimeType("/home/amen/Desktop/sample_h264_1mbit.mp4"));
    }

    @Test
    public void readFile() throws Exception {
        String file = "/home/amen/Desktop/sample_h264_1mbit.mp4";
        BufferedInputStream bis = FileUtil.getBufferedInputStream(file);
        byte[] bs = new byte[1024];
        int len;
        while ((len = bis.read(bs)) > 0) {
            System.out.println(Arrays.toString(Arrays.copyOf(bs, len)));
        }
    }

    @Test
    public void testVote() throws Exception {
        String path = "/home/amen/Desktop/vote_0401.log";
        BufferedReader bufferedReader = FileUtil.getBufferedReader(path);
        Map<String, Set<String>> result = new HashMap<>();
        String voteParamMark = "voteParam=";
        String fp = "fp=";
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Map item = JsonUtil.deserialize(line, Map.class);
            //GET /voteOption/addVote?callback=jQuery11130607229906494972_1522516667621&imgCode=&secret=e915d758e3e3deec5cc9c78290719524%241522516727935&voteParam=CwGNeTj6jX71PTKYqucXWPvV1hKv18JcetSTaxZ%2FRgI%3D&_=1522516667624 HTTP/1.1
            String url = (String) item.get("url");
            String cookie = (String) item.get("cookie");
            int voteParamMarkBegin = url.indexOf(voteParamMark);
            int fpBeginIndex = cookie.indexOf(fp);
            if (voteParamMarkBegin < 0 || fpBeginIndex < 0) {
                System.out.println("invalid item: " + item);
            } else {
                voteParamMarkBegin += voteParamMark.length();
                fpBeginIndex += fp.length();
                int voteParamMarkEnd = url.indexOf("&", voteParamMarkBegin);
                String voteParam = url.substring(voteParamMarkBegin, voteParamMarkEnd);
                String fpToUse = cookie.substring(fpBeginIndex);
                String voteParamToUse = URLDecoder.decode(voteParam, "UTF-8");
                String userId = decode(voteParamToUse);
                Set<String> set = result.computeIfAbsent(userId, key -> new HashSet<>());
                set.add(fpToUse);
            }
        }
        bufferedReader.close();
        for (Map.Entry<String, Set<String>> entry : result.entrySet()) {
            System.out.println("选手: " + entry.getKey() + ", 票数: " + entry.getValue().size());
        }
    }

    @Test
    public void zipFileTest() throws IOException {
        String[] paths = {
            "/home/amen/Downloads/测试文件.txt",
            "/home/amen/Downloads/init.sql"
        };
        File outputZipFile = new File("/home/amen/test.zip");
        FileUtil.zip(outputZipFile, paths);
    }

    @Test
    public void zipDirectoryTest() throws IOException {
        String[] paths = {
            "/home/amen/test-zip",
            "/home/amen/upload"
        };
        File outputZipFile = new File("/home/amen/test.zip");
        FileUtil.zip(outputZipFile, paths);
    }

    @Test
    public void zipFileMappingTest() throws IOException {
        Map<String, List<String>> fileMappings = new HashMap<>();

        List<String> plainFiles = new ArrayList<>();
        plainFiles.add("/home/amen/tmp/tmp1.txt");
        plainFiles.add("/home/amen/tmp/tmp2.txt");
        fileMappings.put("default", plainFiles);

        List<String> directory1 = new ArrayList<>();
        directory1.add("/home/amen/test-zip");
        directory1.add("/home/amen/upload");
        fileMappings.put("directory1", directory1);

        List<String> directory2 = new ArrayList<>();
        directory2.add("/home/amen/upload");
        fileMappings.put("directory2", directory2);

        File outputZipFile = new File("/home/amen/test.zip");
        FileUtil.zip(outputZipFile, fileMappings);
    }

    @Test
    public void zipFileMappingWithRenameTest() throws IOException {
        Map<String, List<Map.Entry<String, String>>> fileMappings = new HashMap<>();

//        List<Map.Entry<String, String>> plainFiles = new ArrayList<>();
//        plainFiles.add(new AbstractMap.SimpleEntry<>("/home/amen/tmp/tmp1.txt", "root-file-1"));
//        plainFiles.add(new AbstractMap.SimpleEntry<>("/home/amen/tmp/tmp2.txt", "root-file-2"));
//        fileMappings.put("default", plainFiles);
//
//        List<Map.Entry<String, String>> directory1 = new ArrayList<>();
//        directory1.add(new AbstractMap.SimpleEntry<>("/home/amen/test-zip", "directory1-file-1"));
//        directory1.add(new AbstractMap.SimpleEntry<>("/home/amen/upload", "directory1-file-2"));
//        fileMappings.put("directory1", directory1);
//
//        List<Map.Entry<String, String>> directory2 = new ArrayList<>();
//        directory2.add(new AbstractMap.SimpleEntry<>("/home/amen/upload", "directory2-file-1"));
//        fileMappings.put("directory2", directory2);
//
//        File outputZipFile = new File("/home/amen/test.zip");
//        FileUtil.zipWithRename(outputZipFile, fileMappings);

        List<Map.Entry<String, String>> plainFiles = new ArrayList<>();
        plainFiles.add(new AbstractMap.SimpleEntry<>("/Users/yangjian/workace/test/single-file/1.txt", "root-file-1"));
        plainFiles.add(new AbstractMap.SimpleEntry<>("/Users/yangjian/workace/test/single-file/2.txt", "root-file-2"));
        fileMappings.put("default", plainFiles);

        List<Map.Entry<String, String>> directory1 = new ArrayList<>();
        directory1.add(new AbstractMap.SimpleEntry<>("/Users/yangjian/workace/test/dir1/dir1-file1", "directory1-file-1"));
        directory1.add(new AbstractMap.SimpleEntry<>("/Users/yangjian/workace/test/dir1/dir1-file2", "directory1-file-2"));
        fileMappings.put("directory1", directory1);

        List<Map.Entry<String, String>> directory2 = new ArrayList<>();
        directory2.add(new AbstractMap.SimpleEntry<>("/Users/yangjian/workace/test/dir2/dir2-file1", "directory2-file-1"));
        directory2.add(new AbstractMap.SimpleEntry<>("/Users/yangjian/workace/test/dir2/dir2-file2", "directory2-file-2"));
        fileMappings.put("directory2", directory2);

        File outputZipFile = new File("/Users/yangjian/workace/test/test.zip");
        FileUtil.zipWithRename(outputZipFile, fileMappings);


    }


    @Test
    public void unzipTest() throws IOException {
        String source = "/home/amen/test.zip";
        String target = "/home/amen/hahaha";
        FileUtil.unzip(source, target);
    }

    @Test
    public void hexToFileTest() throws IOException {
        String targetFile = "/home/amen/tmp/filt-tmp/a";
        String hex = "00009527000001ac0000000101030000bd1a74af0da9a57702eb23b0e3bf16800acdf2915f25703b51da330953d76fd17405958eed314c99ebf8cf22cff60b23afe04bbe7c09b457aac0510028932fa0aacf499b710224b8c4e939c204410b0b6c409e230e3dc26e9f23dbc18897fbdf7ff7884ae349d8e1f6f10d8bf1410cdae2effc8a615612039e8c7a3739b71d87226f8a42438347728063305a41ef8ff1ecd48a9712cccb8a67464997414540b886a05b3a37eaf28649dd6c5d4614e6ddc9b75c5903c94507a38e6861296118bdbdac16227978759f4f23a663d59edbc3cfd0758b5946eb114baf993107bf278d10eab8f5beac429b731928ee8984d9ff813aad782b3650580977c9cf82fad2fd656917df34175247136381ccbd956dd70cbd586daff69fbfdff5d1d30830482be0ba81fcd10c3f4a9c7ad195498561d44ca8b0ae8780d41bc030808d1529380509597269441d391465cc8fe3444c4595709e65fb39354322bc90c60aa925c2b854e04bb3e5d41b205bbb81b406ab8981c31a16f3331eeac72897304b1680187cce8498f6d3ae927d7661842b360628475dae7df015749ba60499467f";
        byte[] bytes = HexUtil.stringToBytes(hex);
        FileUtil.saveFileTo(bytes, targetFile);
    }

    @Test
    public void getMappedBufferedReaderTest() throws IOException {
        String filePath = "D:/tmp/cobconsumer/spring.log";
        try (
            BufferedReader br = FileUtil.getMappedBufferedReader(filePath)
        ) {
            br.lines().forEach(line -> System.out.println("read line: " + line));
        }
    }

    @Test
    public void createFileUriTest() {
        System.out.println(FileUtil.createFileUri("/data/app/bumm_btcusd-MMStrategy/persist.json"));
    }

    @Test
    public void extractFileExtensionTest() {
        System.out.println(FileUtil.extractFileExtension("a"));
        System.out.println(FileUtil.extractFileExtension("a."));
        System.out.println(FileUtil.extractFileExtension("a.doc"));
    }
}
