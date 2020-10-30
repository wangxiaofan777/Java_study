package com.wxf.abstractClass;

/**
 * 抽象类的子类
 *
 * @author WangMaoSong
 * @since 2020-10-30 09:20:39
 */
public class Student extends Person {

    @Override
    protected String getDescription() {
        return "I am a student!";
    }
}
