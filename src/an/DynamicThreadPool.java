package an;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicThreadPool implements Executor {
    AtomicInteger ctl = new AtomicInteger(0);
    private int coreThreadSize;
    private int maxThreadSize;
    BlockingDeque<Runnable> workerQueue;

    public DynamicThreadPool(int coreSize, int maxSize, BlockingDeque<Runnable> queue) {
        coreThreadSize = coreSize;
        maxThreadSize = maxSize;
        workerQueue = queue;
    }
    @Override
    public void execute(Runnable command) {
        int size = ctl.get();
        if (size < coreThreadSize) {
            if (!addWorker(command)) {
                reject();
            }
            return;
        }

        if (!workerQueue.offer(command)) {
            if (!addWorker(command)) {
                reject();
            }
        }
    }
    private void reject() {
        System.out.println("reject");
    }

    private boolean addWorker(Runnable firstTask) {
        if (ctl.get() >= maxThreadSize) return false;
        Worker w = new Worker(firstTask);
        w.thread.run();
        ctl.getAndIncrement();
        return true;
    }

    private final class Worker implements Runnable {
        Runnable task;
        Thread thread;
        public Worker(Runnable r) {
            task = r;
            thread = new Thread(this);
        }

        @Override
        public void run() {
            Runnable firstTask = task;
            try {
                while (firstTask != null || (firstTask = getTask()) != null) {
                    firstTask.run();
                    if (ctl.get() > maxThreadSize) {
                        break;
                    }
                    firstTask = null;
                }
            } finally {
                ctl.decrementAndGet();
            }
        }
    }

    private Runnable getTask() {
        while (true) {
            try {
                return workerQueue.take();
            } catch (Exception e) {

            }
        }
    }

    public static void test() {
        DynamicThreadPool pool = new DynamicThreadPool(3, 10, new LinkedBlockingDeque<>());
        pool.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
