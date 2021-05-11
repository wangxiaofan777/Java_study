package com.wxf.eshaop.cache.ha.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;


/**
 * 获取缓存
 */
public class GetterCommand extends HystrixCommand<String> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("GetterCommand");
    private final int id;
    private final String prefixStoredOnRemoteDataStore;

    public GetterCommand(int id, String prefixStoredOnRemoteDataStore) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet"))
                .andCommandKey(GETTER_KEY));
        this.id = id;
        this.prefixStoredOnRemoteDataStore = prefixStoredOnRemoteDataStore;
    }

    @Override
    protected String run() {
        return prefixStoredOnRemoteDataStore + id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    /**
     * Allow the cache to be flushed for this object.
     *
     * @param id argument that would normally be passed to the command
     */
    public static void flushCache(int id) {
        HystrixRequestCache.getInstance(GETTER_KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }

}