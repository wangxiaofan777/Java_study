package com.wxf.eshaop.cache.ha.hystrix.command;

import com.netflix.hystrix.*;
import com.wxf.eshaop.cache.ha.cache.local.BrandCache;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务商服务Command
 */
@Slf4j
public class GetBrandInfoCommand extends HystrixCommand<String> {

    private Long brandId;

    public GetBrandInfoCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetBrandInfoService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetBrandInfoCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetBrandInfoPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(15)
                        .withQueueSizeRejectionThreshold(10)));
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        // 模拟失败
        throw new RuntimeException();
    }

    @Override
    protected String getFallback() {
        log.info("从本地缓存获取数据");
        return BrandCache.longStringMap.get(brandId);
    }
}
