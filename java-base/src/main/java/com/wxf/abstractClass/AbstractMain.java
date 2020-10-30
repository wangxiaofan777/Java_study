package com.wxf.abstractClass;

/**
 * 抽象类测试类
 */
public class AbstractMain {

    public static void main(String[] args) {
        //抽象类不能为new实例化，需要借助非抽象了子类
        Person person = new Student();
        System.out.println(person.getDescription());

    }
}
