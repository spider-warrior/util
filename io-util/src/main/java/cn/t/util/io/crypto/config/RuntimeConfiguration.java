package cn.t.util.io.crypto.config;

import java.io.File;

public class RuntimeConfiguration {

    private File userResource;

    private File repositoryDirectory;

    private File indexDirectory;

    private File dataDirectory;

    private RepositoryConfiguration repositoryConfiguration;

    public File getUserResource() {
        return userResource;
    }

    public void setUserResource(File userResource) {
        this.userResource = userResource;
    }

    public File getRepositoryDirectory() {
        return repositoryDirectory;
    }

    public void setRepositoryDirectory(File repositoryDirectory) {
        this.repositoryDirectory = repositoryDirectory;
    }

    public File getIndexDirectory() {
        return indexDirectory;
    }

    public void setIndexDirectory(File indexDirectory) {
        this.indexDirectory = indexDirectory;
    }

    public File getDataDirectory() {
        return dataDirectory;
    }

    public void setDataDirectory(File dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

}
