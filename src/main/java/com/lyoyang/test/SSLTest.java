package com.lyoyang.test;

import sun.security.jca.ProviderList;
import sun.security.jca.Providers;

import java.security.Provider;
import java.security.Security;
import java.util.Iterator;

public class SSLTest {

    public static void main(String[] args) {
        System.out.println(Security.getProperty("ssl.KeyManagerFactory.algorithm"));
        ProviderList var3 = Providers.getProviderList();
        Provider provider1 = var3.providers().get(3);
        System.out.println(provider1.getName());
        Iterator<Provider.Service> iterator = provider1.getServices().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getAlgorithm());
        }
    }
}
