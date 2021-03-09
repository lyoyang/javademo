package com.lyoyang.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;

public class TransFormTest {


    @Test
    public void transformTest() {

        AtomicBoolean atomicBoolean = new AtomicBoolean();
//        Flux.just("A", "B", "C").transform(stringFlux -> stringFlux.doFinally(signal -> signal.))

    }

}
