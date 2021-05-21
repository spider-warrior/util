package cn.t.util.common.random;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * JDK主键生成替代器
 * 该类使用 {@link SecureRandom} 初始化seed,并且在这之后使用{@link Random}代替{@link UUID#randomUUID()}中每次调用{@link SecureRandom}
 * 该类提供了性能与安全更好的平衡
 */
public class AlternativeJdkIdGenerator {

    private final Random random;

    public AlternativeJdkIdGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = new byte[8];
        secureRandom.nextBytes(seed);
        this.random = new Random(new BigInteger(seed).longValue());
    }

    public UUID generateId() {
        byte[] randomBytes = new byte[16];
        this.random.nextBytes(randomBytes);

        //最高有效 64 位
        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            //(randomBytes[i] & 0xff使得负数转正数(byte的值相同)
            //该阶段把随机串的前八个字节使用或组成一个大数字
            mostSigBits = (mostSigBits << 8) | (randomBytes[i] & 0xff);
        }

        //最低有效 64 位
        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (randomBytes[i] & 0xff);
        }
        return new UUID(mostSigBits, leastSigBits);
    }

}
