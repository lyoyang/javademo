package com.lyoyang.designpattern.singleton;

public class Singleton8 {

    private Singleton8() {
    }

    public static Singleton8 getInstance() {
        return EnumHolder.INSTANCE.getSingleton();
    }

    private enum EnumHolder {
        INSTANCE;
        private Singleton8 instance;

        EnumHolder() {
            this.instance = new Singleton8();
        }

        private Singleton8 getSingleton() {
            return instance;
        }
    }
}
