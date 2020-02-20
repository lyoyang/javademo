package com.lyoyang.concurrent.threadpool;

public interface DenyPolicy {
    void rejeict(Runnable runnable, ThreadPool threadPool);


    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void rejeict(Runnable runnable, ThreadPool threadPool) {

        }
    }


    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void rejeict(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("the runnable " + runnable + "will be abort.");
        }
    }

    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void rejeict(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutDown()) {
                runnable.run();
            }
        }
    }

}
