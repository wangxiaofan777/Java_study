package com.wxf.eshaop.cache.ha.cache.local;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地城市缓存
 */
public class LocationCache {

    private static Map<Long, String> map = new HashMap<>(16);

    static {
        map.put(1L, "北京");
    }

    public static String getCityNameById(Long cityId) {
        return map.get(cityId);
    }
}
