package com.wxf.inventory.config;

import org.ehcache.config.EvictionAdvisor;

public class MyEvictionAdvisor implements EvictionAdvisor<String,Object> {

    @Override
    public boolean adviseAgainstEviction(String s, Object o) {
        return false;
    }
}
