package cn.t.util.io.crypto;

import cn.t.util.io.FileUtil;
import cn.t.util.io.crypto.config.RepositoryConfiguration;
import cn.t.util.io.crypto.config.RuntimeConfiguration;
import cn.t.util.io.crypto.helper.RepositoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileEncrypt {

    private static Logger logger = LoggerFactory.getLogger(FileEncrypt.class);
    private RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
    private RuntimeConfiguration runtimeConfiguration = new RuntimeConfiguration();
    private Context context = new Context();

    private RepositoryHelper repositoryHelper;

    public void encrypt(String target, String to, String resourceName) throws IOException {
        File original = new File(target);
        to = repositoryHelper.completeRepositoryPath(to);
        File repository = new File(to);
        if(!original.exists()) {
            logger.warn("specified file or directory not exist, ignore encrypt");
            return;
        }
        //init configuration
        File indexDirectory = new File(FileUtil.appendFilePath(repository.getAbsolutePath(), context.getRepositoryConfiguration().getIndexDirectoryName()));
        File dataDirectory = new File(FileUtil.appendFilePath(repository.getAbsolutePath(), context.getRepositoryConfiguration().getDataDirectoryName()));
        context.getRuntimeConfiguration().setUserResource(original);
        context.getRuntimeConfiguration().setRepositoryDirectory(repository);
        context.getRuntimeConfiguration().setIndexDirectory(indexDirectory);
        context.getRuntimeConfiguration().setDataDirectory(dataDirectory);

        //init repository
        repositoryHelper.initRepository();

        repositoryHelper.persistData(resourceName, original);
    }

    public FileEncrypt() {
        this.context.setRepositoryConfiguration(repositoryConfiguration);
        this.context.setRuntimeConfiguration(runtimeConfiguration);
        repositoryHelper = new RepositoryHelper(context);
    }
}
