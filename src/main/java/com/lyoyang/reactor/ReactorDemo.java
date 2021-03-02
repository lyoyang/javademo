package com.lyoyang.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author: Brian
 * @Date: 2020/5/22 14:54
 * @Description:
 */
public class ReactorDemo {


    @Test
    public void simpleFluxCreate() throws InterruptedException {
        Flux.just("Hello", "World").subscribe(System.out::print);
        Flux.fromArray(new Integer[]{1,2,3,4}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
        //从0开始递增的Long对象的序列，每格10s产生一个，会并行计算，线程都是守护线程
        Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1000);
    }

    @Test
    public void complexFluxCreate() {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    @Test
    public void createFlux() {
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }


    @Test
    public void createMono() {
        Mono.fromSupplier(() -> "hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("green")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("blue")).subscribe(System.out::println);
    }

    @Test
    public void operator() {
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        //每隔1s生成一个数字，每隔5s从flux序列中取出一个元素，总共取2次
        Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).buffer(Duration.of(5, ChronoUnit.SECONDS));
//                .take(2).toStream().forEach(System.out::println);
        //当是偶数的时候输出序列，会输出50组
        Flux.range(1,100).bufferUntil(i -> i%2 == 0).subscribe(System.out::println);
        //只输出能被3整除的元素
        Flux.range(1, 100).bufferWhile(i -> i%3 == 0).subscribe(System.out::println);
    }


    @Test
    public void fluxWithFilter() {
        Flux.range(1, 10).filter(i -> i%2 == 0).subscribe(System.out::println);
    }


    @Test
    public void fluxWithWindow() {
        Flux.range(1, 100).window(20).subscribe(System.out::println);
    }

    @Test
    public void fluxWithReduce() {
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        // give a default value
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);
    }

    @Test
    public void fluxWithMerge() {
        Flux.merge(Flux.interval(Duration.of(100, ChronoUnit.MILLIS)).take(5), Flux.interval(Duration.of(150, ChronoUnit.MILLIS)).take(5))
                .toStream().forEach(System.out::println);

        Flux.merge(Flux.range(1, 3), Flux.range(4, 3))
                .toStream().forEach(System.out::println);

        Flux.mergeSequential(Flux.interval(Duration.of(100, ChronoUnit.MILLIS)).take(5), Flux.interval(Duration.of(150, ChronoUnit.MILLIS)).take(5))
                .toStream().forEach(System.out::println);

    }



    @Test
    public void fluxWithFlatMap() {
        Flux.just(5, 10).flatMap(x -> Flux.just(x*10)).toStream()
                .forEach(System.out::println);
    }

    @Test
    public void msgProcess() {
        Flux.just(1, 2).concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(-1)
                .subscribe(System.out::println);

    }

    @Test
    public void scheduler() {
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }





}
