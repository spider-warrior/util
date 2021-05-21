package cn.t.util.security.message.encryption.pbe;

/**
 * PBE(Password Based Encryption)基于口令加密 + 盐
 * 对称加密
 * PBE结合了消息摘要算法和对称加密算法的优点
 * <p>
 * 使用方式:
 * 1.发送者构建口令
 * 2.公布口令给接收者
 * 3.构建盐
 * 4.使用口令,盐对数据加密
 * 5.发送盐、加密数据
 * 6.使用口令、盐对数据解密
 */
