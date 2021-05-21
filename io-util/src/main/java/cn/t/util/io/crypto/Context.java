package cn.t.util.io.crypto;

import cn.t.util.io.crypto.config.RepositoryConfiguration;
import cn.t.util.io.crypto.config.RuntimeConfiguration;

public class Context {

    private RepositoryConfiguration repositoryConfiguration;

    private RuntimeConfiguration runtimeConfiguration;

    public RepositoryConfiguration getRepositoryConfiguration() {
        return repositoryConfiguration;
    }

    public void setRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) {
        this.repositoryConfiguration = repositoryConfiguration;
    }

    public RuntimeConfiguration getRuntimeConfiguration() {
        return runtimeConfiguration;
    }

    public void setRuntimeConfiguration(RuntimeConfiguration runtimeConfiguration) {
        this.runtimeConfiguration = runtimeConfiguration;
    }
}
