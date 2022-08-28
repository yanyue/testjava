package advancedjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class YyCas {
    private final static  int COUNT = 100;
    private final static int THREAD_SIZE = 3;
    private AtomicInteger index = new AtomicInteger(0);

    public static void test() {
        YyCas cas = new YyCas();
        cas.work();
    }
    public void work() {
        List<Object> objList = Collections.synchronizedList(new ArrayList<Object>());
        for (int i = 0;i < 3; ++i) {
            new Thread() {
                @Override
                public void run() {
                    int idx = index.getAndIncrement();
                    while (idx <= 100) {
                        objList.add(new Object());
                        idx = index.incrementAndGet();
                    }
                }
            }.start();
        }

        try {
            Thread.sleep(1000);
            System.out.println("objList count=" + objList.size());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
