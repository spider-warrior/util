package cn.t.util.io.test.encrypto;

import cn.t.util.io.crypto.FileDecrypt;
import cn.t.util.io.crypto.FileEncrypt;
import org.junit.Test;

import java.io.IOException;

public class FileEncryptTest {

    @Test
    public void encryptTest() throws IOException {
        String resourceName = "my-test";
        String target = "/home/amen/Pictures/aaa";
        String to = "";
        FileEncrypt encrypt = new FileEncrypt();
        encrypt.encrypt(target, to, resourceName);
    }

    @Test
    public void decryptTest() throws IOException {
        String repository = "/home/amen/workspace/idea/my/util/io-util/repository";
        String resourceName = "my-test";
        String target = "/home/amen/Pictures/ccc";
        FileDecrypt decrypt = new FileDecrypt();
        decrypt.decrypt(repository, resourceName, target);
    }
}
