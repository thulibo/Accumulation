package interview;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author libo
 *
 */
public class ConcurrentLock {

    private static final ReentrantLock lock = new ReentrantLock(true);
    private static volatile int count = 1;

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {

            @Override
            public void run() {
                while (count < 100) {
                    if (lock.tryLock()) {
                        if (count < 100 && count % 2 != 0) {
                            System.out.println("ThreadA-" + count);
                            count++;
                        }
                        lock.unlock();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {

            @Override
            public void run() {
                while (count < 100) {
                    if (lock.tryLock()) {
                        if (count < 100 && count % 2 == 0) {
                            System.out.println("ThreadB-" + count);
                            count++;
                        }
                        lock.unlock();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

        while (count < 100) {
            try {
                System.out.println("sleeped.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
