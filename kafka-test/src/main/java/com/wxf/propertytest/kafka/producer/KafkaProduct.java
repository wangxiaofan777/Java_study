package com.wxf.propertytest.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProduct {

    private static KafkaProducer kafkaProducer;
    private static Properties properties = new Properties();

    static {
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.211:9092,192.168.1.212:9092,192.168.1.213:9092");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "redismaster:9092,redisnode1:9092,redisnode2:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducer = new KafkaProducer(properties);
    }

    public static void main(String[] args) {

        String topic = "access-log";
        String message = "{\"id\":\"https://news.now.com/home/local/player?newsId=430695\",\"author\":\"\",\"area\":\"newsnow\",\"url\":\"https://news.now.com/home/local/player?newsId=430695\",\"title\":\"【晨早新聞重點】(4月12日)\",\"content\":\"【Now新聞台】4月12日now晨早新聞重點 【美國務卿關注台海局勢及新疆問題】美國國務卿布林肯警告任何人試圖以武力改變西太平洋現狀將犯下嚴重錯誤，他又指北京對待新疆維吾爾族人的行為等同企圖種族滅絕。 【歐盟官員：中俄阻礙應對緬甸局勢】緬甸多處繼續有示威抗議軍方發動政變，有歐盟官員批評中俄兩國阻撓國際社會應對緬甸局勢。 【目標暑假接受登記】政府選定四間儲值支付工具協助發放五千元電子消費券，目標在暑假期間接受登記。有商戶指若豁免手續費會考慮安裝電子支付。\",\"news_time\":\"2021-04-12 07:03:43\",\"keyword\":[\"布林肯\",\"欧盟\",\"美国\",\"缅甸\",\"新疆维吾尔族人\",\"Now新闻台】\",\"北京\",\"新疆\"],\"language\":\"cht\",\"collect_time\":\"2021-04-12 10:23:49\",\"news_clean_time\":\"2021-04-12 10:23:49\",\"image\":[\"bb55b036875b3b7d59bbfd5d12457490\",\"05162dd193db199cc62cd7d2fe5bd5a9\",\"0c8e000d7aa6f4927f520f1ad7450199\",\"efb3121f0adda20fcec91b8eeb999c4c\",\"34e4b7977e514b8d0b2e3bc903770a2f\",\"eeaad61c507c27e814d6729f483fb9bd\",\"35137a213b80e1ed07439da2e50d431b\",\"3c99fd6f00567cd94c190dbf93052021\",\"de97dbb5de59b3f322958c2d4d6e211d\",\"7e92d917d01bf618f1138d4452430d0f\"],\"label\":[\"政治\"],\"nation\":\"hk\",\"rawid\":\"https://news.now.com/home/local/player?newsId=430695\",\"content_raw\":\"【Now新聞台】4月12日now晨早新聞重點 【美國務卿關注台海局勢及新疆問題】美國國務卿布林肯警告任何人試圖以武力改變西太平洋現狀將犯下嚴重錯誤，他又指北京對待新疆維吾爾族人的行為等同企圖種族滅絕。 【歐盟官員：中俄阻礙應對緬甸局勢】緬甸多處繼續有示威抗議軍方發動政變，有歐盟官員批評中俄兩國阻撓國際社會應對緬甸局勢。 【目標暑假接受登記】政府選定四間儲值支付工具協助發放五千元電子消費券，目標在暑假期間接受登記。有商戶指若豁免手續費會考慮安裝電子支付。\",\"report_time\":\"2021-04-12 07:03:43\",\"cleaning_time\":\"2021-04-12 10:23:49\",\"vertex_id\":\"news_61eced13bb4ff8d748d422be1e594fbf\",\"content_length\":228}";
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, message);
        try {
            kafkaProducer.send(record).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
