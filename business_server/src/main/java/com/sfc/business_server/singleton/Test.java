package com.sfc.business_server.singleton;
//单例模式
public class Test {

    //懒汉式单例类.在第一次调用的时候实例化自己
    private Test() {}
    private static Test test=null;
    //静态工厂方法
    public static Test getTest() {
        if (test == null) {
            test = new Test();
            System.out.println("创建一次");
        }
        return test;
    }

    public void show(){
        System.out.println("我是show");
    }

}
