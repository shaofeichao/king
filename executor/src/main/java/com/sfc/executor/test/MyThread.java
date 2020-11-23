package com.sfc.executor.test;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "执行完成！");
    }
}
