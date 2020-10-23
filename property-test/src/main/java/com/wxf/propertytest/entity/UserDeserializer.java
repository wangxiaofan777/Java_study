package com.wxf.propertytest.entity;


import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * USer实体类Deserializer
 *
 * @author WangMaoSong
 * @since 2020-10-23 14:12:08
 */
public class UserDeserializer implements Deserializer<User> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public User deserialize(String topic, byte[] data) {
        ByteArrayInputStream arrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            arrayInputStream = new ByteArrayInputStream(data);
            objectInputStream = new ObjectInputStream(arrayInputStream);
            User user = (User) objectInputStream.readObject();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (arrayInputStream !=null) {
                try {
                    arrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    @Override
    public User deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}
