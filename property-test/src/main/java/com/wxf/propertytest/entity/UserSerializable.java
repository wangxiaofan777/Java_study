package com.wxf.propertytest.entity;


import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


/**
 * User实体类Serializer
 *
 * @author WangMaoSong
 * @since 2020-10-23 14:12:38
 */
public class UserSerializable implements Serializer<User> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, User data) {
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, User data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
