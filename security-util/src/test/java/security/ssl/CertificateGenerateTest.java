package security.ssl;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Enumeration;

/**
 * 参考地址
 * <a href="https://gamlor.info/posts-output/2019-10-29-java-create-certs-bouncy/en/">Create HTTPS Certificates in Java with Bouncy Castle</a>
 * 1.将issuer证书使用mmc导入到信任的证书办法机构
 * 2.p12密钥库提取证书和私钥配置在nginx
 * openssl pkcs12 -in abc-cert.p12 -out abc.key -nodes -nocerts
 * openssl pkcs12 -in abc-cert.p12 -out abc.crt -nokeys
 */
public class CertificateGenerateTest {

    final static class GeneratedCert {
        public final PrivateKey privateKey;
        public final X509Certificate certificate;
        public GeneratedCert(PrivateKey privateKey, X509Certificate certificate) {
            this.privateKey = privateKey;
            this.certificate = certificate;
        }
    }

    @Test
    public void generateTest() throws Exception {
        GeneratedCert rootCrt = createCertificate("do_not_trust_test_certs_root",   /*issuer=*/null,  /*isCa=*/true);
        GeneratedCert issuerCrt = createCertificate("do_not_trust_test_certs_issuer", rootCrt,           /*isCa=*/true);
        GeneratedCert domainCrt = createCertificate("abc cert",                       issuerCrt,           /*isCa=*/false,              "abc.com", "*.abc.com");
        char[] emptyPassword = new char[0];
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        // Key store expects a load first to initialize.
        keyStore.load(null, emptyPassword);
        // Store our domain certificate, with the private key and the cert chain
        persistKeyStore(
            keyStore,
            emptyPassword,
            "root-crt", rootCrt,
            new X509Certificate[]{rootCrt.certificate},
            "D:\\tmp\\ssl\\java-ssl\\root-cert.p12"
        );
        clearKeyStore(keyStore);
        persistKeyStore(
            keyStore,
            emptyPassword,
            "issuer-crt", issuerCrt,
            new X509Certificate[]{issuerCrt.certificate, rootCrt.certificate},
            "D:\\tmp\\ssl\\java-ssl\\issuer-cert.p12"
        );
        clearKeyStore(keyStore);
        persistKeyStore(
            keyStore,
            emptyPassword,
            "abc-crt",
            domainCrt,
            new X509Certificate[]{domainCrt.certificate, issuerCrt.certificate, rootCrt.certificate},
            "D:\\tmp\\ssl\\java-ssl\\abc-cert.p12"
        );
        clearKeyStore(keyStore);
    }

    private void clearKeyStore(KeyStore keyStore) throws KeyStoreException {
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            keyStore.deleteEntry(alias);
        }
    }

    private void persistKeyStore(KeyStore keyStore, char[] password, String alias, GeneratedCert generatedCert, X509Certificate[] chain, String path) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        keyStore.setKeyEntry(alias, generatedCert.privateKey, password, chain);
        try (FileOutputStream os = new FileOutputStream(path)) {
            keyStore.store(os, password);
        }
    }

    private static GeneratedCert createCertificate(String cnName, GeneratedCert issuer, boolean isCA, String... domains) throws Exception {
        // Generate the key-pair with the official Java API's
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        KeyPair certKeyPair = keyGen.generateKeyPair();
        X500Name name = new X500Name("CN=" + cnName);
        // If you issue more than just test certificates, you might want a decent serial number schema ^.^
        BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());
        Instant validFrom = Instant.now();
        Instant validUntil = validFrom.plus(10 * 360, ChronoUnit.DAYS);

        // If there is no issuer, we self-sign our certificate.
        X500Name issuerName;
        PrivateKey issuerKey;
        if (issuer == null) {
            issuerName = name;
            issuerKey = certKeyPair.getPrivate();
        } else {
            issuerName = new X500Name(issuer.certificate.getSubjectDN().getName());
            issuerKey = issuer.privateKey;
        }
        JcaX509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
            issuerName,
            serialNumber,
            Date.from(validFrom),
            Date.from(validUntil),
            name,
            certKeyPair.getPublic()
        );
        // Make the cert to a Cert Authority to sign more certs when needed
        if (isCA) {
            builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(isCA));
        }
        // Modern browsers demand the DNS name entry
        if (domains != null && domains.length > 0) {
            GeneralName[] generalNames = new GeneralName[domains.length];
            for (int i = 0; i < domains.length; i++) {
                generalNames[i] = new GeneralName(GeneralName.dNSName, domains[i]);
            }
            builder.addExtension(Extension.subjectAlternativeName, false,
                new GeneralNames(generalNames));
        }
        // Finally, sign the certificate
        ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA").build(issuerKey);
        X509CertificateHolder certHolder = builder.build(signer);
        X509Certificate cert = new JcaX509CertificateConverter().getCertificate(certHolder);
        return new GeneratedCert(certKeyPair.getPrivate(), cert);
    }
}
