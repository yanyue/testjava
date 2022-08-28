package lock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试ReentrantLock可中断锁的效果
 */
public class ThreadInteruptDemo {
    ReentrantLock lock1=new ReentrantLock();
    ReentrantLock lock2=new ReentrantLock();

    /**
     * ReentrantLock响应中断
     * @throws Exception
     */
    public void exeInterupt() throws Exception{
        Thread t1=new Thread(new DemoThread(lock1,lock2));
        Thread t2=new Thread(new DemoThread(lock2,lock1));
        t1.start();
        t2.start();
        System.out.println(t1.getName()+"中断");
        //主线程睡眠1秒，避免线程t1直接响应run方法中的睡眠中断
        Thread.sleep(1000);
        t1.interrupt();
        //阻塞主线程，避免所有线程直接结束，影响死锁效果
        Thread.sleep(10000);
    }

    Object syn1=new Object();
    Object syn2=new Object();

    /**
     * Synchronized响应中断
     * @throws Exception
     */
    public void exeInteruptSyn() throws Exception{
        Thread t1=new Thread(new DemoThread1(syn1,syn2));
        Thread t2=new Thread(new DemoThread1(syn2,syn1));
        t1.start();
        t2.start();
        System.out.println(t1.getName()+"中断");
        //主线程睡眠1秒，避免线程t1直接响应run方法中的睡眠中断
        Thread.sleep(1000);
        t1.interrupt();
        //阻塞主线程，避免所有线程直接结束，影响死锁效果
        Thread.sleep(10000);
    }

    void alternateTask() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 65; i < 91; i++) {
                    System.out.println("----------thread1------- " + (char) i);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.println("----------thread2------- " + i);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        });
        thread1.start();
        thread2.start();
    }

    /**
     * ReentrantLock实现死锁
     */
    static class DemoThread implements Runnable{

        ReentrantLock lock1;
        ReentrantLock lock2;

        public DemoThread(ReentrantLock lock1,ReentrantLock lock2){
            this.lock1=lock1;
            this.lock2=lock2;
        }

        @Override
        public void run() {
            try {
                //可中断的获取锁
                lock1.lockInterruptibly();
                System.out.println("线程"+Thread.currentThread().getName()+" 11");
                //lock1.lock();
                //睡眠200毫秒，保证两个线程分别已经获取到两个锁，实现相互的锁等待
                TimeUnit.MILLISECONDS.sleep(200);
                //lock2.lock();
                //可中断的获取锁
                System.out.println("线程"+Thread.currentThread().getName()+" 22");
                lock2.lockInterruptibly();
                System.out.println("线程"+Thread.currentThread().getName()+" 33");
            } catch (InterruptedException e) {
                System.out.println("线程"+Thread.currentThread().getName()+" 异常了");
                e.printStackTrace();
            }finally {
                lock1.unlock();
                lock2.unlock();
                System.out.println("线程"+Thread.currentThread().getName()+"正常结束");
            }

        }
    }

    /**
     * Synchronized实现死锁
     */
    static class DemoThread1 implements Runnable{

        Object lock1;
        Object lock2;

        public DemoThread1(Object lock1,Object lock2){
            this.lock1=lock1;
            this.lock2=lock2;
        }

        @Override
        public void run() {
            try {
                synchronized (lock1){
                    //睡眠200毫秒，再获取另一个锁，
                    //保证两个线程分别已经获取到两个锁，实现相互的锁等待
                    TimeUnit.MILLISECONDS.sleep(200);
                    synchronized (lock2){
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("线程"+Thread.currentThread().getName()+"正常结束");
            }

        }
    }
}