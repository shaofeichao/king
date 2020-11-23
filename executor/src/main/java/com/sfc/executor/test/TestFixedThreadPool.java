package com.sfc.executor.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFixedThreadPool {
    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * @param args
     */
    public static void main(String[] args) {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        for (int i = 0; i < 10; i++) {
            Thread t = new MyThread();
            pool.execute(t);
        }
        //关闭线程池
        pool.shutdown();
        System.out.println("shutdown后" + pool.isTerminated());
        while (true) {
            System.out.println("等待所有线程释放...");
            if (pool.isTerminated()) {
                System.out.println("所有线程执行完了！");
                break;
            }
        }
    }
}
