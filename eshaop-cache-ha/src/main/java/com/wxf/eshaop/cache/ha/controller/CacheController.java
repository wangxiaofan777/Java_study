package com.wxf.eshaop.cache.ha.controller;


import com.netflix.hystrix.HystrixCommand;
import com.wxf.eshaop.cache.ha.http.HttpClientUtils;
import com.wxf.eshaop.cache.ha.hystrix.command.GetCityNameCommand;
import com.wxf.eshaop.cache.ha.hystrix.command.GetProductInfoCommand;
import com.wxf.eshaop.cache.ha.hystrix.command.GetProductInfosCommand;
import com.wxf.eshaop.cache.ha.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;


@Slf4j
@RestController
public class CacheController {

    @GetMapping(value = "/change/product")
    public String changeProduct(Long productId) {
        // 数据同步的需要通过消息队列去更新，这里是为了方便直接HTTP方式
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        return "success";
    }

    /**
     * Nginx开始，各级缓存都失效了，Nginx发送很多的请求直接到缓存服务要求，要求拉取最原始的数据
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/getProductInfo")
    public String getProductInfo(Long productId) {
        HystrixCommand<ProductInfo> productInfoCommand = new GetProductInfoCommand(productId);
        // 同步
        ProductInfo productInfo = productInfoCommand.execute();
        GetCityNameCommand getCityNameCommand = new GetCityNameCommand(productInfo.getCityId());
        productInfo.setCityName(getCityNameCommand.execute());
        System.out.println(productInfo);
        return "success";
    }


    /**
     * 批量获取商品
     *
     * @param productIds
     * @return
     */
    @GetMapping(value = "/getProductInfos")
    public String getProductInfos(String productIds) {
        GetProductInfosCommand productInfosCommand = new GetProductInfosCommand(productIds.split(","));
        Observable<ProductInfo> construct = productInfosCommand.observe();
        construct.subscribe(new Observer<ProductInfo>() {
            @Override
            public void onCompleted() {
                log.info("获取完毕所有商品");
            }

            @Override
            public void onError(Throwable e) {
                log.error("异常", e);
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                log.info(productInfo.toString());
            }
        });
        return "success";
    }
}
