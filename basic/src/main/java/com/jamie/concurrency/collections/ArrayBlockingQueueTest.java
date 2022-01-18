package com.jamie.concurrency.collections;

import com.jamie.concurrency.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.*;

public class ArrayBlockingQueueTest {

    /**
     * 往满的数组阻塞队列 添加元素
     */
    @Test
    public void blockingQueuePutTest() {

//        ArrayBlockingQueue()
//        根据参数容量，实例化数组对象
//        this.items = new Object[capacity];
//
//        实例化一把非公平锁
//        lock = new ReentrantLock(fair);
//
//        信号通知condition
//        notEmpty = lock.newCondition();
//        notFull =  lock.newCondition();
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        ThreadUtil.execute(() -> {
            try {

//                put
//                acquireInterruptibly 拿锁，拿不到就自旋
//                lock.lockInterruptibly();
//                    队列已满
//                    while (count == items.length)
//                        生产者等待通知
//                        notFull.await();
//                    队列还没满就加入数组
//                    enqueue(e);

//                enqueue
//                往指针位置加入数组
//                items[putIndex] = x;
//                数组满了就重置指针
//                if (++putIndex == items.length)
//                    putIndex = 0;
//                计算数组的元素个数
//                count++;
//                通知消费者来消费
//                notEmpty.signal();
                queue.put(1);
                System.out.println("加入1");
                queue.put(2);
                System.out.println("加入2");
                //此处 Thread 断点
                queue.put(3);
                System.out.println("加入3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ThreadUtil.execute(() -> {
            try {
//                take
//                acquireInterruptibly 拿锁，拿不到就自旋
//                lock.lockInterruptibly();
//                    数组没有元素
//                    while (count == 0)
//                        消费者等待通知
//                        notEmpty.await();
//                    数组还有元素，取元素
//                    return dequeue();

//                dequeue
//                根据指针从数组取出元素
//                E x = (E) items[takeIndex];
//                删除指针对应的元素
//                items[takeIndex] = null;
//                取到最后一位元素，重置指针
//                if (++takeIndex == items.length)
//                    takeIndex = 0;
//                计算数组元素个数
//                count--;
//                通知生产者去生产
//                notFull.signal();
                //此处 Thread 断点
                queue.take();
                System.out.println("取走第一个元素");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        while (true) {
        }
    }
}
