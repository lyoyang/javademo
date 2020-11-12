package com.lyoyang.netty.http;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;

public class SSLContextFactory {

    public static SSLContext getSslContext() throws Exception {
        char[] filePass = "123456".toCharArray();
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        KeyStore keyStore = KeyStore.getInstance("JKS");
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("cert/sChat.jks");
        keyStore.load(resourceAsStream, filePass);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, filePass);
        sslContext.init(kmf.getKeyManagers(), null, null);
        return sslContext;
    }

}
