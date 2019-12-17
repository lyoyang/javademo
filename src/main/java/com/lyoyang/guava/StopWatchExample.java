package com.lyoyang.guava;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class StopWatchExample {


    @Test
    public void testStopWatch() throws InterruptedException {
        Stopwatch started = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("the main thread cost " + started.stop());
    }



}
