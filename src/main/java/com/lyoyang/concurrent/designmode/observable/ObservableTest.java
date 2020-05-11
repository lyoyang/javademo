package com.lyoyang.concurrent.designmode.observable;

import java.util.concurrent.TimeUnit;

/**
 * @author: yangbing
 * @Date: 2020/2/24 13:46
 * @Description:
 */
public class ObservableTest {

    public static void main(String[] args) {
//        ObsevableThread obsevableThread = new ObsevableThread<>(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("finished");
//            return null;
//        });
//        obsevableThread.start();


        TaskLifeCycle.DefaultTaskLifeCycle<String> lifeCycle = new TaskLifeCycle.DefaultTaskLifeCycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("the result is " + result);
            }
        };

        ObsevableThread thread = new ObsevableThread<>(lifeCycle, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finished");
            return "finished";
        });
        thread.start();

    }

}
