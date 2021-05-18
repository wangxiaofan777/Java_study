package com.wxf.eshaop.cache.ha.cache.local;

import java.util.HashMap;
import java.util.Map;

public class BrandCache {
    public static Map<Long, String> longStringMap = new HashMap<>(16);

    static {
        longStringMap.put(1L, "iphone");
    }
}
