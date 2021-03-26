package com.wxf.inventory.service.impl;

import com.wxf.inventory.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        this.jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return this.jedisCluster.get(key);
    }

    @Override
    public void delete(String key) {
        this.jedisCluster.del(key);
    }
}
