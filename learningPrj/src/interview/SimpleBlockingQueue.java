package interview;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现BlockingQueue，只实现主要方法
 * 
 * 
 * @author libo09@mails.tsinghua.edu.cn
 *
 */
public class SimpleBlockingQueue<T> {

    /**
     * 阻塞队列容量大小，默认16
     */
    private volatile int maxSize = 16;

    public SimpleBlockingQueue() {

    }

    public SimpleBlockingQueue(int maxCapacity) {
        this.maxSize = maxCapacity;
    }

    /**
     * 阻塞队列内部的list
     */
    private final List<T> innerList = new LinkedList<T>();

    /**
     * 向队列尾部添加元素，如果队列满了，阻塞等待队列有空位
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param element
     */
    synchronized public void put(T element) {
        if (innerList.size() < maxSize) {
            this.notify();
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        innerList.add(element);
    }

    /**
     * 从队列头部弹出元素，如果队列空了，阻塞等待队列有元素加入
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @return
     */
    synchronized public T take() {
        if (innerList.size() > 0) {
            this.notify();
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return innerList.remove(0);
    }

    /**
     * Just For Test
     * 
     * @author libo09@mails.tsinghua.edu.cn
     * 
     * @param args
     */
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<Integer>(4);
        // 生产
        final AtomicInteger counter = new AtomicInteger(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    simpleBlockingQueue.put(counter.incrementAndGet());
                }
            }
        }).start();

        // 消费
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("===== " + simpleBlockingQueue.take());
                }
            }
        }).start();

        // 防止主线程退出
        while (simpleBlockingQueue.innerList.size() > 0) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
