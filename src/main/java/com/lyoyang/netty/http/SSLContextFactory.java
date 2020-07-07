package com.lyoyang.netty.http;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyStore;

public class SSLContextFactory {

    public static SSLContext getSslContext() throws Exception {
        char[] filePass = "123456".toCharArray();
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        KeyStore keyStore = KeyStore.getInstance("JKS");
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("cert/sChat.jks");
        keyStore.load(resourceAsStream, filePass);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keyStore, filePass);
        sslContext.init(kmf.getKeyManagers(), null, null);
        return sslContext;
    }

}
