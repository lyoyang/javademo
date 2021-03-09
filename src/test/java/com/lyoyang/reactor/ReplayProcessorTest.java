package com.lyoyang.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;
import reactor.test.StepVerifier;

public class ReplayProcessorTest {


    @Test
    public void replayProcessor() {
        ReplayProcessor<String> processor = ReplayProcessor.create(2, false);
        produce(processor.sink());
        consume(processor);
    }

    private void produce(FluxSink<String> slink) {
        slink.next("1");
        slink.next("2");
        slink.next("3");
        slink.complete();
    }

    private void consume(Flux<String> publisher) {
        for (int i = 0; i < 5; i++) {
            StepVerifier.create(publisher).expectNext("3").expectNext("3").verifyComplete();
        }
    }


}
