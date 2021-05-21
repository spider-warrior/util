package cn.t.util.io.crypto;

import cn.t.util.io.FileUtil;
import cn.t.util.io.crypto.config.RepositoryConfiguration;
import cn.t.util.io.crypto.config.RuntimeConfiguration;
import cn.t.util.io.crypto.helper.RepositoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileDecrypt {

    private static Logger logger = LoggerFactory.getLogger(FileDecrypt.class);
    private RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
    private RuntimeConfiguration runtimeConfiguration = new RuntimeConfiguration();
    private Context context = new Context();

    private RepositoryHelper repositoryHelper;

    public void decrypt(String repositoryStr, String resourceName, String  target) throws IOException {
        File repository = new File(repositoryStr);
        if(!repository.exists()) {
            logger.warn("specified repository not exist");
            return;
        }
        //init configuration
        File indexDirectory = new File(FileUtil.appendFilePath(repository.getAbsolutePath(), context.getRepositoryConfiguration().getIndexDirectoryName()));
        File dataDirectory = new File(FileUtil.appendFilePath(repository.getAbsolutePath(), context.getRepositoryConfiguration().getDataDirectoryName()));
        context.getRuntimeConfiguration().setRepositoryDirectory(repository);
        context.getRuntimeConfiguration().setIndexDirectory(indexDirectory);
        context.getRuntimeConfiguration().setDataDirectory(dataDirectory);

        repositoryHelper.recoverData(resourceName, new File(target));
    }

    public FileDecrypt() {
        this.context.setRepositoryConfiguration(repositoryConfiguration);
        this.context.setRuntimeConfiguration(runtimeConfiguration);
        repositoryHelper = new RepositoryHelper(context);
    }
}
