package com.wxf.propertytest.entity;


import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        byte[] byteArray = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
            byteArray = outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteArray;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, User data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
