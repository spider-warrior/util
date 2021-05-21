package cn.t.util.http;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class KeyStoreUtil {

    private static KeyStore getPkcs12KeyStore() throws KeyStoreException {
        return KeyStore.getInstance(CertificateType.PKCS12);
    }

    private static KeyStore getSunX509KeyStore() throws KeyStoreException {
        return KeyStore.getInstance(CertificateType.SUNX509);
    }

    private static KeyStore getJksKeyStore() throws KeyStoreException {
        return KeyStore.getInstance(CertificateType.JKS);
    }

    private static KeyManagerFactory getSunX509KeyManagerFactory() throws NoSuchAlgorithmException {
        return KeyManagerFactory.getInstance(CertificateType.SUNX509);
    }

    private static TrustManagerFactory getSunX509TrustManagerFactory() throws NoSuchAlgorithmException {
        return TrustManagerFactory.getInstance(CertificateType.SUNX509);
    }

    public static KeyStore loadCertificate(String certificateType, String path, char[] password) throws IOException, CertificateException, NoSuchAlgorithmException, InvalidCertificateType, KeyStoreException {
        KeyStore keyStore = getKeyStore(certificateType);
        keyStore.load(new FileInputStream(path), password);
        return keyStore;
    }

    public static KeyManagerFactory initSunX509KeyManagerFactory(KeyStore keyStore, char[] password) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        KeyManagerFactory keyManagerFactory = getSunX509KeyManagerFactory();
        keyManagerFactory.init(keyStore, password);
        return keyManagerFactory;
    }

    public static TrustManagerFactory initSunX509TrustManagerFactory(KeyStore keyStore) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = getSunX509TrustManagerFactory();
        trustManagerFactory.init(keyStore);
        return trustManagerFactory;
    }

    public static SSLContext initSSLContext(KeyManagerFactory keyManagerFactory, TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
        return sc;
    }

    public static SSLConnectionSocketFactory getAllowAllHostnameVerifier(SSLContext sc) {
        return new SSLConnectionSocketFactory(sc, new NoopHostnameVerifier());
    }

    private static KeyStore getKeyStore(String certificateType) throws KeyStoreException, InvalidCertificateType {
        if (CertificateType.PKCS12.equalsIgnoreCase(certificateType)) {
            return getPkcs12KeyStore();
        } else if (CertificateType.SUNX509.equalsIgnoreCase(certificateType)) {
            return getSunX509KeyStore();
        } else if (CertificateType.JKS.equalsIgnoreCase(certificateType)) {
            return getJksKeyStore();
        } else {
            throw new InvalidCertificateType(certificateType);
        }
    }

}
