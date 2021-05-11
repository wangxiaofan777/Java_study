package com.wxf.eshaop.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


/**
 * 设置缓存
 */
public class SetterCommand extends HystrixCommand<Void> {

    private final int id;
    private final String prefix;
    private String prefixStoredOnRemoteDataStore;

    public SetterCommand(int id, String prefix, String prefixStoredOnRemoteDataStore) {
        super(HystrixCommandGroupKey.Factory.asKey("GetSetGet"));
        this.id = id;
        this.prefix = prefix;
        this.prefixStoredOnRemoteDataStore = prefixStoredOnRemoteDataStore;
    }

    @Override
    protected Void run() {
        // persist the value against the datastore
        prefixStoredOnRemoteDataStore = prefix;
        // flush the cache
        GetterCommand.flushCache(id);
        // no return value
        return null;
    }
}