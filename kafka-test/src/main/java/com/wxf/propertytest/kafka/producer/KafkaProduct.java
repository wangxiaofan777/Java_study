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
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.211:9092,192.168.1.212:9092,192.168.1.213:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducer = new KafkaProducer(properties);
    }

    public static void main(String[] args) {
        String topic = "qb_DI2NLP_news";
        String message = "{\"id\":\"https://www.rt.com/news/520266-china-chizhou-fire-mall-smoke/\",\"author\":\"\",\"area\":\"rt\",\"url\":\"https://www.rt.com/news/520266-china-chizhou-fire-mall-smoke/\",\"title\":\"4 reported killed as fire sweeps through Chinese shopping mall, sending column of smoke towering above city (VIDEO)\",\"content\":\"On Tuesday, the Chizhou Fire Rescue Detachment dispatched 12 vehicles to the scene of a blaze which was reported around 9am local time at the Tongluowan Commercial Plaza at Dongjiekou. The emergency services succeeded in extinguishing the flames, which reportedly first broke out on the top floor of the complex, and recovered six people who were trapped. All six were sent to the city’s hospital for treatment, two of whom were later discharged, local media reported. Footage shared online via Chinese social media showed smoke billowing up from the building, growing in intensity before the emergency services arrived at the scene. Police and other emergency service personnel were also filmed outside the building. #中共國在燃燒 4月6日，安徽池州一商业广场发生火灾。现场火势较大，浓烟滚滚⋯⋯ pic.twitter.com/csUYm6EBGw The Chizhou Fire Rescue Detachment said that an investigation into how the fire started was underway, and called on citizens not to believe in – or spread – rumors, but instead wait for an official update on the cause.If you like this story, share it with a friend!\",\"news_time\":\"2021-04-06 15:31:00\",\"keyword\":[\"中共國在燃燒\",\"building#中共國在燃燒\",\"Chinese\"],\"language\":\"en\",\"collect_time\":\"2021-04-07 03:23:55\",\"news_clean_time\":\"2021-04-07 03:23:55\",\"image\":[\"1e95d8090ba83da5b1e7ddd9224212a8\",\"2ac14502a2fe11a1603bb56a2fdedc7f\"],\"label\":[\"娱乐\"],\"rawid\":\"https://www.rt.com/news/520266-china-chizhou-fire-mall-smoke/\",\"content_raw\":\"On Tuesday, the Chizhou Fire Rescue Detachment dispatched 12 vehicles to the scene of a blaze which was reported around 9am local time at the Tongluowan Commercial Plaza at Dongjiekou. The emergency services succeeded in extinguishing the flames, which reportedly first broke out on the top floor of the complex, and recovered six people who were trapped. All six were sent to the city’s hospital for treatment, two of whom were later discharged, local media reported. Footage shared online via Chinese social media showed smoke billowing up from the building, growing in intensity before the emergency services arrived at the scene. Police and other emergency service personnel were also filmed outside the building. #中共國在燃燒 4月6日，安徽池州一商业广场发生火灾。现场火势较大，浓烟滚滚⋯⋯ pic.twitter.com/csUYm6EBGw The Chizhou Fire Rescue Detachment said that an investigation into how the fire started was underway, and called on citizens not to believe in – or spread – rumors, but instead wait for an official update on the cause.If you like this story, share it with a friend!\",\"report_time\":\"2021-04-06 15:31:00\",\"cleaning_time\":\"2021-04-07 03:23:55\",\"vertex_id\":\"news_71f1f1c5d635d86188fd3a7cb3743647\",\"content_length\":1051}";
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
