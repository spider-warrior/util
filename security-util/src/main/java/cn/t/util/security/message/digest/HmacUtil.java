package cn.t.util.security.message.digest;

import cn.t.util.security.message.AlgorithmName;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * MAC(Message Authentication Code)
 * HMAC(keyed-hashed Message Authentication Code) 含有密钥的散列函数算法
 * 融合MD、SHA
 * - MD系列:  HmacMD2、HmacMD4、HmacMD5
 * - SHA系列: HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512
 * 应用: SecureCRT
 * <p>
 * 1.发送方公布消息摘要算法
 * 2.发送方构建密钥
 * 3.发送方发送密钥
 * 4.发送方对消息进行摘要处理
 * 5.发送方发送摘要信息
 * 6.发送方发送消息
 * 7.接收方消息鉴别
 */
public class HmacUtil {

    /**
     * 转换encoded key
     * @param keyBytes xxx
     * @return xxx
     */
    public static Key convertSecretKey(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, AlgorithmName.HMAC_MD5);
    }
}
