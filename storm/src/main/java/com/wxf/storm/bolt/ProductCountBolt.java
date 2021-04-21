package com.wxf.storm.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.trident.util.LRUMap;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;


/**
 * 商品访问次数统计Bolt
 */
public class ProductCountBolt extends BaseRichBolt {

    private LRUMap<Long, Long> productCountMap = new LRUMap<>(100);

    @Override
    public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    @Override
    public void execute(Tuple tuple) {
        Long productId = tuple.getLongByField("productId");
        Long countMapOrDefault = productCountMap.getOrDefault(productId, 0L);
        if (countMapOrDefault != 0L)
            countMapOrDefault++;

        productCountMap.put(productId, countMapOrDefault);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(""));
    }
}
