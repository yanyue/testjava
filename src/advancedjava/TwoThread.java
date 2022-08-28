package advancedjava;

class Worker implements Runnable {
    volatile static int num = 1;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num++;
                } else {
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
public class TwoThread {
    public void test() {
        Worker w = new Worker();
        Thread t1 = new Thread(w, "线程1");
        Thread t2 = new Thread(w, "线程2");
        t1.start();
        t2.start();
        System.out.println("finished");
    }
}
