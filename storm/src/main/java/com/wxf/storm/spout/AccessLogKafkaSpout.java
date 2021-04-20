package com.wxf.storm.spout;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * Kafka热门商品消费Spout
 */
public class AccessLogKafkaSpout extends BaseRichSpout {

    private SpoutOutputCollector spoutOutputCollector;
    private static ArrayBlockingQueue<String> queues = new ArrayBlockingQueue<>(100);

    @Override
    public void open(Map<String, Object> map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
        this.startKafkaConsumer();
    }

    @Override
    public void nextTuple() {
        if (queues.size() > 0) {
            try {
                String take = queues.take();
                this.spoutOutputCollector.emit(new Values(take));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }

    public static void main(String[] args) {
        AccessLogKafkaSpout accessLogKafkaSpout = new AccessLogKafkaSpout();
        accessLogKafkaSpout.startKafkaConsumer();
    }

    private void startKafkaConsumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "redismaster:9092,redisnode1:9092,redisnode2:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        properties.put("group.id", "group1");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList("access-log"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ZERO);
            records.forEach(record -> {
                try {
                    Thread.currentThread().sleep(100L);
                    queues.put(record.value());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
