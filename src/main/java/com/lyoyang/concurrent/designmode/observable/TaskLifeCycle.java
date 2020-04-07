package com.lyoyang.concurrent.designmode.observable;


/**
 * @author: yangbing
 * @Date: 2020/2/24 11:28
 * @Description:
 */
public interface TaskLifeCycle<T> {
     void onStart(Thread thread);

     void onRunning(Thread thread);

     void onFinish(Thread thread, T result);

     void onError(Thread thread, Exception e);

     class DefaultTaskLifeCycle<T> implements TaskLifeCycle<T> {
         @Override
         public void onStart(Thread thread) {

         }

         @Override
         public void onRunning(Thread thread) {

         }

         @Override
         public void onFinish(Thread thread, T result) {

         }

         @Override
         public void onError(Thread thread, Exception e) {

         }
     }


}
