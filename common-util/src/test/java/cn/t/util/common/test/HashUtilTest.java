package cn.t.util.common.test;


import cn.t.util.common.HashUtil;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class HashUtilTest {

    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        System.out.println(HashUtil.md5("123456"));
    }

    @Test
    public void sha1Test() throws NoSuchAlgorithmException {
        System.out.println(HashUtil.sha1("123456"));
    }
}
