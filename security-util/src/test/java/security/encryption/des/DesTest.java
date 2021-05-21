package security.encryption.des;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * DES
 */
public class DesTest {

    private static final String src = "this is message";

    public static void main(String[] args) {
        jdkDes();
        bcDes();
    }

    public static void jdkDes() {
        try {
            //1.生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
//            SecretKey secretKey = keyGenerator.generateKey();
            String strDefaultKey = "DA43C108DA5BF4708975CD0B3E8C20C2B945CE583489F192";
            Key secretKey = getKey(strDefaultKey.getBytes());
            byte[] bytesKey = secretKey.getEncoded();
            System.out.println(new String(bytesKey));

            //2.Key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            Key convertedSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//加密方式/工作模式/填充方式
            cipher.init(Cipher.ENCRYPT_MODE, convertedSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk des encrypt: " + Hex.toHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertedSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk des decrypt: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcDes() {
        try {

            //添加provider
            Security.addProvider(new BouncyCastleProvider());

            //1.生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            keyGenerator.getProvider();
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //2.Key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            secretKeyFactory.generateSecret(desKeySpec);
            Key convertedSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//加密方式/工作模式/填充方式
            cipher.init(Cipher.ENCRYPT_MODE, convertedSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("bc des encrypt: " + Hex.toHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertedSecretKey);
            result = cipher.doFinal(result);
            System.out.println("bc des decrypt: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void abcTest() throws Exception {

//        String hex = "c9bb3fae6f12500fbbcc5088272ce72dd0cbdb0c3fc444be95ba344aac9ed034edf686b5d31b9635d84f775dd55e85b1678b7384bf1aca81f29e2b5d21589607d25f23391c6477c69e162d2679db55a8e7bf51ff52415b552d6dc199e00165c75066698b2ee1bfaef3049510fa0825c3f3d50e66c5e5f7b027b67b52aee0abb063c12ea545ae22f2825af5e4c3d1cc53";
//        String hex = "c9bb3fae6f12500fbbcc5088272ce72dd0cbdb0c3fc444bea723e7cf3c73ef5fd52db7f59e25c2828fb9a1310080ea4e043c20cfffb26ad22f2f1faabd14763459ee010071a62cc9f7b1f5975200a31f1b214144b5ff5015d76c55c941861ca15d5e69934927cc4b12f4beed7179768335c10551666cf0c3b8b0a117517e9194d7c34680a557d6598e5acc528bf9f1fcffbf4fe961d72664d53a6bf189b36ac126f5a61d3afe3b186095f6c275bf8d33874fe2873080236a9fe25e2abd3007049635a7cf1a38e95ab593caddf0d3dcafba9a84fc785fcfbc35026c16fadf0546";
//        String hex = "c9bb3fae6f12500fbbcc5088272ce72dd0cbdb0c3fc444be95ba344aac9ed034edf686b5d31b9635d84f775dd55e85b1ce0ea4a2e5c9a49472671fc98e4a729395f5398c52b95991994f6e409f85cd5a01b144c8078454c2edda85db68ebce47e56232c84df0e511cb8d6c7ce8f2dff7f37db6b1a39daa2fb8b0a117517e9194d7c34680a557d6598134afae341d968edad05f8b2e4876b414c1e2c93d1459f126f5a61d3afe3b185373cc459539413ba5d6ad05003782891b76b7bdc9d655495ac98e69f69f6769e4f13f308b25e1c2763b49a2db759b73712789178e13b59f";
//        String hex = "c3d193e8baa6d9cff4d83e1eb6ad6507f33911e7196a01be3324d00ba54ffc410b83964486785fce9fcd23200b69d4ab63c12ea545ae22f2e3172915d0a2a685056730c663bcae7e9cdcff058862f2c1193592990770df05e9a23a05a88a885dcf32eea277692ca93bee28d02d241ffe8656d8210a19dcc219aec1328be3bfd34fb8962305f6d647b34cb28b3f0bed5b7563e9045e96d76cf37abf440c2b8a9738808a7897993ba024e813f6aba39b54a5ef24a545260a184e6f30a2d40a02be3e4615826092d05a76dbd416d5a84e1daaf7a1dbe2915f91bc1cb456d6fd8b9a1b76b7bdc9d655495ac98e69f69f6769383f9a9058f3650be18dcb259e0c9a4b53d49d89d669345bf2579bda320ac345100b5b5a555266f138b161ea2c08d13fa5d7b6d20384133cf82acf71cc2091868d45f316b32a53bd7a41524fd23c24b570d1879f2a426202df4eeff8fcb2c9a1364f98c66ba395b22f3448ebb2dac8f080668ff40b65dd5484f1204453db279e5fad6f645df2b5f68ed5e2f063647f97bcb5b0e7bc5b32bc3a66b658435b5938698f25028522b5dc143ee7b13df1c8a6a104416eeae17fdfa09ed0bdf8226010a400f3b87317f39d836a3397ef60edc1d6fc12df317d3aedb9a24b7b3b51c6507f8f4f6c37269039";
//
//        String strDefaultKey = "DA43C108DA5BF4708975CD0B3E8C20C2B945CE583489F192";
//        Security.addProvider(new SunJCE());
//        Key key = getKey(strDefaultKey.getBytes());
//
//        Cipher encryptCipher = Cipher.getInstance("DES");
//        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
//
//        Cipher decryptCipher = Cipher.getInstance("DES");
//        decryptCipher.init(Cipher.DECRYPT_MODE, key);
//
//        byte []decryptBytes = decryptCipher.doFinal(HexUtil.stringToBytes(hex));
//        System.out.println(new String(decryptBytes));
//    }


    private static Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8]; // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        return new SecretKeySpec(arrB, "DES");
    }
}
