package com.wxf.storm.bolt;

import com.alibaba.fastjson.JSONObject;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;


/**
 * 日志格式化Bolt
 */
public class LogParseBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String message = tuple.getStringByField("message");
        JSONObject messageObject = JSONObject.parseObject(message);
        JSONObject uri_args = messageObject.getJSONObject("uri_args");
        String productId = uri_args.getString("productId");
        outputCollector.emit(new Values(productId));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("productId"));
    }
}
