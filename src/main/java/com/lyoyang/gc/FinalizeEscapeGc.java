package com.lyoyang.gc;

public class FinalizeEscapeGc {

    public static FinalizeEscapeGc SAVE_HOOK = null;

    public void isAlived() {
        System.out.println("yes,i am stills alive...");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGc.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGc();
        //d对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //finalize优先级很低，暂停0.5秒等待
        Thread.sleep(500);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlived();
        } else {
            System.out.println("no, i am dead...");
        }
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlived();
        } else {
            System.out.println("no, i am dead...");
        }

    }

}
