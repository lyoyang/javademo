package com.lyoyang.concurrent.monitorthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MonitorThreadPool extends ThreadPoolExecutor {

    private String poolName;

    private ConcurrentHashMap<String, Date> startTimes;


    public MonitorThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, String poolName) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new EventThreadFactory(poolName), poolName);
    }


    public MonitorThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, String poolName) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.poolName = poolName;
    }

    @Override
    public void shutdown() {
        log.info("{} going to shutdown.Executed tasks:{},Running Tasks:{}, Pending tasks:{}",
                this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        log.info("{} going to shutdown now.Executed tasks:{},Running Tasks:{}, Pending tasks:{}",
                this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTimes.put(String.valueOf(r.hashCode()), new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
        Date finishDate = new Date();
        long costTime = finishDate.getTime() - startDate.getTime();
        log.info("{}-pool-monitor,Duration:{} ms, PoolSize:{}, CorePoolSize:{}, Active:{}, " +
                "Completed:{}, Task:{}, Queue:{}, LargestPoolSize:{}," +
                "MaximumPoolSize:{}, KeepAliveTime:{}, isShutdown:{}, isTerminated:{}",
                this.poolName, this.getPoolSize(), this.getCorePoolSize(), this.getActiveCount(),
                this.getCompletedTaskCount(), this.getTaskCount(), this.getQueue().size(), this.getLargestPoolSize(),
                this.getMaximumPoolSize(), this.getKeepAliveTime(TimeUnit.MILLISECONDS), this.isShutdown(), this.isTerminated());
    }

    public static ExecutorService newFixedThreadPool(int nThreads, String poolName) {
        return new MonitorThreadPool(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), poolName);
    }

    public static ExecutorService newCachedThreadPool(String poolName) {
        return new MonitorThreadPool(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), poolName);
    }

    static class EventThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup group;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;


        public EventThreadFactory(String poolName) {
            SecurityManager s = System.getSecurityManager();

            group = Objects.nonNull(s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();

            namePrefix = poolName + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
        }


        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


}
