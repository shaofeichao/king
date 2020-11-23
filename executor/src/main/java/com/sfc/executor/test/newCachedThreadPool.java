package com.sfc.executor.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newCachedThreadPool {
    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
     * @param args
     */
    public static void main(String[] args) {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        for (int i = 0;i < 10; i++) {
            //将线程放入池中进行执行
            Thread t = new MyThread();
            pool.execute(t);
        }
        //关闭线程池
        pool.shutdown();
    }
}
