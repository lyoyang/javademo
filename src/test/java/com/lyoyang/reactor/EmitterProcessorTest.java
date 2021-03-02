package com.lyoyang.reactor;

import org.junit.Test;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

public class EmitterProcessorTest {


    @Test
    public void emitterProcessor() {
        EmitterProcessor<String> processor = EmitterProcessor.create();
        producr(processor.sink());
        consume(processor);
    }

    private void producr(FluxSink<String> slink) {
        slink.next("1");
        slink.next("2");
        slink.next("3");
        slink.next("4");
        slink.complete();
    }

    private void consume(Flux<String> publisher) {
        StepVerifier.create(publisher).expectNext("1").expectNext("2").expectNext("3").expectNext("4")
                .verifyComplete();
    }


}
