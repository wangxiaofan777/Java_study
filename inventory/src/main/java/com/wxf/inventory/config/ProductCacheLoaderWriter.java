package com.wxf.inventory.config;

import org.ehcache.spi.loaderwriter.CacheLoaderWriter;

public class ProductCacheLoaderWriter implements CacheLoaderWriter<String, Object> {

    @Override
    public Object load(String s) throws Exception {
        return null;
    }

    @Override
    public void write(String s, Object o) throws Exception {

    }

    @Override
    public void delete(String s) throws Exception {

    }
}
