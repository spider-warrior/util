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
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 参考地址
 * <a href="https://gamlor.info/posts-output/2019-10-29-java-create-certs-bouncy/en/">Create HTTPS Certificates in Java with Bouncy Castle</a>
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
        GeneratedCert rootCA = createCertificate("do_not_trust_test_certs_root",   /*domain=*/null,     /*issuer=*/null,  /*isCa=*/true);
        GeneratedCert issuer = createCertificate("do_not_trust_test_certs_issuer", /*domain=*/null,     rootCA,           /*isCa=*/true);
        GeneratedCert domain = createCertificate("local.gamlor.info",              "local.gamlor.info", issuer,           /*isCa=*/false);
        GeneratedCert otherD = createCertificate("other.gamlor.info",              "other.gamlor.info", issuer,           /*isCa=*/false);
        char[] emptyPassword = new char[0];
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        // Key store expects a load first to initialize.
        keyStore.load(null, emptyPassword);
        // Store our domain certificate, with the private key and the cert chain
        keyStore.setKeyEntry("local.gamlor.info", domain.privateKey, emptyPassword,
            new X509Certificate[]{domain.certificate, issuer.certificate, rootCA.certificate});
        keyStore.setKeyEntry("other.local.gamlor.info", otherD.privateKey, emptyPassword,
            new X509Certificate[]{otherD.certificate, issuer.certificate, rootCA.certificate});
        // Store to a file
        try (FileOutputStream store = new FileOutputStream("D:\\tmp\\ssl\\java-ssl\\my-cert.pfx")) {
            keyStore.store(store, emptyPassword);
        }
    }

    private static GeneratedCert createCertificate(String cnName, String domain, GeneratedCert issuer, boolean isCA) throws Exception {
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
        if (domain != null) {
            builder.addExtension(Extension.subjectAlternativeName, false,
                new GeneralNames(new GeneralName(GeneralName.dNSName, domain)));
        }
        // Finally, sign the certificate
        ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA").build(issuerKey);
        X509CertificateHolder certHolder = builder.build(signer);
        X509Certificate cert = new JcaX509CertificateConverter().getCertificate(certHolder);
        return new GeneratedCert(certKeyPair.getPrivate(), cert);
    }
}
