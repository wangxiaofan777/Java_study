package com.wxf.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Jedis配置
 */
@Component
public class RedisConfig {
/*
    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> hostAndPorts = new HashSet<>(6);
        hostAndPorts.add(new HostAndPort("10.1.100.20", 7001));
        hostAndPorts.add(new HostAndPort("10.1.100.20", 7002));
        hostAndPorts.add(new HostAndPort("10.1.100.21", 7003));
        hostAndPorts.add(new HostAndPort("10.1.100.21", 7004));
        hostAndPorts.add(new HostAndPort("10.1.100.22", 7005));
        hostAndPorts.add(new HostAndPort("10.1.100.22", 7006));
        return new JedisCluster(hostAndPorts);
    }*/

}
