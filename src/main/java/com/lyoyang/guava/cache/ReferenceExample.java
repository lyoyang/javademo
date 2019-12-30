package com.lyoyang.guava.cache;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 虚拟机参数：
 * -Xmx128M -Xms64M -XX:+PrintGCDetails
 */
public class ReferenceExample {

    public static void main(String[] args) throws InterruptedException {
//        strongRef();
//        softRef();
        weakRef();
//        phantomRef();

    }



    /**
     * 强引用
     * @throws InterruptedException
     */
    public static void strongRef() throws InterruptedException {
        int counter = 1;
        List<Ref> list = new ArrayList<>();
        for(;;) {
            int current = counter++;
            list.add(new Ref(counter));
            System.out.println("the " + current + " will be add to the list.");
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    /**
     * 软引用
     * @throws InterruptedException
     */
    public static void softRef() throws InterruptedException {
        int counter = 1;
        List<SoftReference<Ref>> list = new ArrayList<>();
        for(;;) {
            int current = counter++;
            list.add(new SoftReference<>(new Ref(counter)));
            System.out.println("the " + current + " will be add to the list.");
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    /**
     * 弱引用
     * @throws InterruptedException
     */
    public static void weakRef() throws InterruptedException {
        int counter = 1;
        List<WeakReference<Ref>> list = new ArrayList<>();
        for(;;) {
            int current = counter++;
            list.add(new WeakReference<>(new Ref(counter)));
            System.out.println("the " + current + " will be add to the list.");
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }


    /**
     * 幻想引用
     * @throws InterruptedException
     */
    public static void phantomRef() throws InterruptedException {

        Ref ref = new Ref(20);
        ReferenceQueue queue = new ReferenceQueue<>();
        MyPhantomReference reference = new MyPhantomReference(ref, queue, 23);
        ref = null;
        System.out.println(reference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        MyPhantomReference object = (MyPhantomReference) queue.remove();
        object.doAction();

    }



    private static class MyPhantomReference extends PhantomReference<Object> {

        private int index;

        public MyPhantomReference(Object referent, ReferenceQueue<? super Object> q, int index) {
            super(referent, q);
            this.index = index;
        }
        public void doAction() {
            System.out.println("the object " + index + " have GC.");
        }
    }







    private static class Ref {
        private final int index;
        // 1M
        private byte[] data = new byte[1024*1024];

        public Ref(int index) {
            this.index = index;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("the index [" + index + "] will be GC.");
        }
    }

}
