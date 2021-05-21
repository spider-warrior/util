package cn.t.util.io.crypto.config;

import cn.t.util.io.FileConstants;

public class RepositoryConfiguration {

    /**
     * 资源库文件夹名称
     * */
    private String repositoryDirectoryName = "repository";

    /**
     * index directory
     * */
    private String indexDirectoryName = "index";

    /**
     * data directory
     * */
    private String dataDirectoryName = "data";

    /**
     * data path separator
     * */
    private String dataPathSeparator = ",";
    
    /**
     * 索引桶数
     * */
    private int indexBucketSize = 26;

    /**
     * chunk size
     * */
    private int unitChunkSize = FileConstants.MB * 60;


    public String getRepositoryDirectoryName() {
        return repositoryDirectoryName;
    }

    public void setRepositoryDirectoryName(String repositoryDirectoryName) {
        this.repositoryDirectoryName = repositoryDirectoryName;
    }

    public String getIndexDirectoryName() {
        return indexDirectoryName;
    }

    public void setIndexDirectoryName(String indexDirectoryName) {
        this.indexDirectoryName = indexDirectoryName;
    }

    public String getDataDirectoryName() {
        return dataDirectoryName;
    }

    public void setDataDirectoryName(String dataDirectoryName) {
        this.dataDirectoryName = dataDirectoryName;
    }

    public int getIndexBucketSize() {
        return indexBucketSize;
    }

    public void setIndexBucketSize(int indexBucketSize) {
        this.indexBucketSize = indexBucketSize;
    }

    public int getUnitChunkSize() {
        return unitChunkSize;
    }

    public void setUnitChunkSize(int unitChunkSize) {
        this.unitChunkSize = unitChunkSize;
    }

    public String getDataPathSeparator() {
        return dataPathSeparator;
    }

    public void setDataPathSeparator(String dataPathSeparator) {
        this.dataPathSeparator = dataPathSeparator;
    }
}
