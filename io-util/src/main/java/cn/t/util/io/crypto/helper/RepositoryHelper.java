package cn.t.util.io.crypto.helper;

import cn.t.util.common.StringUtil;
import cn.t.util.io.FileUtil;
import cn.t.util.io.crypto.Context;
import cn.t.util.io.crypto.entity.PersistDataResult;
import cn.t.util.io.crypto.entity.ResourceIndex;
import cn.t.util.io.crypto.exception.InitRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class RepositoryHelper {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryHelper.class);
    private Context context;
    private FileIndexHelper fileIndexHelper;
    private FileDataHelper fileDataHelper;

    public String completeRepositoryPath(String target) {
        if(StringUtil.isEmpty(target)) {
            target = System.getProperty("user.dir");
            logger.info("specified repository path is empty, use current directory instead: {}", target);
        }
        return FileUtil.appendFilePath(target, context.getRepositoryConfiguration().getRepositoryDirectoryName());
    }

    public void initRepository() throws IOException {
        if(!context.getRuntimeConfiguration().getRepositoryDirectory().exists()) {
            boolean success = context.getRuntimeConfiguration().getRepositoryDirectory().mkdirs();
            if(!success) {
                throw new InitRepositoryException("初始化仓库失败");
            }
            doInitRepository();
        }
    }

    public void persistData(String resourceKey, File original) throws IOException {
        byte[] resourceKeyBytes = resourceKey.getBytes();
        int index = fileIndexHelper.queryResourceIndex(resourceKeyBytes);
        String indexPath = FileUtil.appendFilePath(context.getRuntimeConfiguration().getIndexDirectory().getAbsolutePath(), String.valueOf(index));
        try(
            FileChannel indexChannel = FileChannel.open(Paths.get(indexPath), StandardOpenOption.READ, StandardOpenOption.WRITE)
        ) {
            //检查是否已经存在
            ResourceIndex resourceIndex = fileIndexHelper.queryResourceIndex(indexChannel, resourceKeyBytes);
            if(resourceIndex != null) {
                logger.warn("specified resource already exists, ignore persist");
                return;
            }
            //查询保存块索引
            byte chunkIndex = fileIndexHelper.queryChunkIndex(indexChannel);
            PersistDataResult persistDataResult = fileDataHelper.encryptAndPersistData(original, index, chunkIndex);
            fileIndexHelper.persistFileIndex(indexChannel,
                resourceKeyBytes,
                persistDataResult.getDataPath(),
                persistDataResult.getDataLength(),
                persistDataResult.getStartIndex(),
                persistDataResult.getEndIndex());
        }
    }

    public void recoverData(String resourceKey, File to) throws IOException {
        byte[] resourceKeyBytes = resourceKey.getBytes();
        int index = fileIndexHelper.queryResourceIndex(resourceKeyBytes);
        String indexPath = FileUtil.appendFilePath(context.getRuntimeConfiguration().getIndexDirectory().getAbsolutePath(), String.valueOf(index));
        try(
            FileChannel indexChannel = FileChannel.open(Paths.get(indexPath), StandardOpenOption.READ, StandardOpenOption.WRITE)
        ) {
            //检查是否已经存在
            ResourceIndex resourceIndex = fileIndexHelper.queryResourceIndex(indexChannel, resourceKeyBytes);
            if(resourceIndex != null) {
                if(!FileUtil.initDirectory(to)) {
                    logger.error("初始化目录失败: {}", to.getAbsolutePath());
                    return;
                }
                String dataStorageDirectoryPath = FileUtil.appendFilePath(context.getRuntimeConfiguration().getDataDirectory().getAbsolutePath(), String.valueOf(index));
                byte[] dataPaths = resourceIndex.getDataPath();
                if(dataPaths != null && dataPaths.length > 0) {
                    logger.info("即将还原资源: {}, 创建于: {}", resourceKey, new Date(resourceIndex.getCrTime()));

                }
            } else {
                logger.warn("specified resource not exists, ignore recover");
            }
        }
    }

    public void doRecoverData(ResourceIndex resourceIndex, int index, File to) {
        String dataStorageDirectoryPath = FileUtil.appendFilePath(context.getRuntimeConfiguration().getDataDirectory().getAbsolutePath(), String.valueOf(index));
        byte[] dataPaths = resourceIndex.getDataPath();
        if(dataPaths != null && dataPaths.length > 0) {
            logger.info("即将还原资源: {}, 创建于: {}", new String(resourceIndex.getResourceKeyBytes()), new Date(resourceIndex.getCrTime()));
            long startIndex = resourceIndex.getStartIndex();
            long endIndex = resourceIndex.getEndIndex();

        }
    }

    private void doInitRepository() throws IOException {
        initIndexDir(context.getRuntimeConfiguration().getIndexDirectory());
        initDataDir(context.getRuntimeConfiguration().getDataDirectory());
    }

    private void initIndexDir(File indexDirectory) throws IOException {
        if(!FileUtil.initDirectory(indexDirectory)) {
            throw new InitRepositoryException("创建repository索引文件夹失败");
        }
        logger.info("初始化索引文件夹, bucket sie: {}", context.getRepositoryConfiguration().getIndexBucketSize());
        for(int i=0; i<context.getRepositoryConfiguration().getIndexBucketSize(); i++) {
            String indexBucket = FileUtil.appendFilePath(indexDirectory.getAbsolutePath(), String.valueOf(i));
            if(!FileUtil.initFile(new File(indexBucket))) {
                throw new InitRepositoryException(String.format("创建第%d索引文件失败", i+1));
            } else {
                try (
                    DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(indexBucket))
                ){
                    dataOutputStream.writeByte(0);
                    dataOutputStream.flush();
                }
            }
        }
    }

    private void initDataDir(File dataDirectory) throws IOException {
        if(!FileUtil.initDirectory(dataDirectory)) {
            throw new InitRepositoryException("创建repository数据文件夹失败");
        }
        for(int i=0; i<context.getRepositoryConfiguration().getIndexBucketSize(); i++) {
            String indexDataDir = FileUtil.appendFilePath(dataDirectory.getAbsolutePath(), String.valueOf(i));
            if(!FileUtil.initDirectory(new File(indexDataDir))) {
                throw new InitRepositoryException(String.format("创建第%d索引文件失败", i+1));
            }
        }
    }

    public RepositoryHelper(Context context) {
        this.context = context;
        fileIndexHelper = new FileIndexHelper(context);
        fileDataHelper = new FileDataHelper(context);
    }
}
