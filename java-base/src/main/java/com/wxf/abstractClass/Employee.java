package com.wxf.abstractClass;

/**
 * 抽象类的子类Employee
 *
 * @author WangMaoSong
 * @since 2020-10-30 09:22:03
 */
public class Employee extends Person {

    @Override
    protected String getDescription() {
        return "I am a employee!";
    }
}
