package com.wxf.inventory.controller;

import com.wxf.inventory.entity.ProductInventoryCnt;
import com.wxf.inventory.request.InventoryCntDBUpdateRequest;
import com.wxf.inventory.request.ProductInventoryCntReloadRequest;
import com.wxf.inventory.request.Request;
import com.wxf.inventory.response.Response;
import com.wxf.inventory.service.ProductInventoryCntService;
import com.wxf.inventory.service.RequestAsyncProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存controller
 */
@RestController
public class ProductInventoryController {

    @Autowired
    private RequestAsyncProcessorService requestAsyncProcessorService;
    @Autowired
    private ProductInventoryCntService productInventoryCntService;

    /**
     * 更新商品库存
     *
     * @param inventoryCnt
     * @return
     */
    @GetMapping(value = "/updateProductInventory")
    @ResponseBody
    public Response updateProductInventory(ProductInventoryCnt inventoryCnt) {
        Response response;
        try {
            Request request = new InventoryCntDBUpdateRequest(inventoryCnt, productInventoryCntService);
            requestAsyncProcessorService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }

    /**
     * 获取商品缓存
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/getProductInventory")
    @ResponseBody
    public ProductInventoryCnt getProductInventory(Integer productId) {
        ProductInventoryCnt productInventoryCnt;
        try {
            Request request = new ProductInventoryCntReloadRequest(productId, productInventoryCntService, false);
            this.requestAsyncProcessorService.process(request);

            // 讲请求扔给Service之后，需要while(true)去等待前面的更新缓存操作处理完，将缓存更新到缓存队列中
            long startTime = System.currentTimeMillis();
            long entTime = 0L;
            long waitTime = 0L;
            while (true) {
                // 超过200ms，超时则直接返回
                if (waitTime > 200)
                    break;

                productInventoryCnt = this.productInventoryCntService.getProductInventoryCntCache(productId);

                // 结果不为空，则直接返回
                if (productInventoryCnt != null)
                    return productInventoryCnt;

                // 结果为空，则休眠20ms，再请求
                Thread.currentThread().sleep(20);
                entTime = System.currentTimeMillis();
                waitTime = entTime - startTime;

            }

            // 从缓存中没有获取到再从DB读取,若果可以读到，则直接返回，否则返回-1
            productInventoryCnt = this.productInventoryCntService.findProductInventoryCnt(productId);
            if (productInventoryCnt != null) {
                // 强制刷新缓存
                request = new ProductInventoryCntReloadRequest(productId, productInventoryCntService, true);
                this.requestAsyncProcessorService.process(request);
                return productInventoryCnt;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductInventoryCnt(productId, -1L);
    }
}
