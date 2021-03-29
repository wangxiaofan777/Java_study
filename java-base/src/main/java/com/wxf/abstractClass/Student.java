package com.wxf.abstractClass;

/**
 * 抽象类的子类
 *
 * @author WangXiaofan777
 * @since 2020-10-30 09:20:39
 */
public class Student extends Person {

    @Override
    protected String getDescription() {
        return "I am a student!";
    }
}
