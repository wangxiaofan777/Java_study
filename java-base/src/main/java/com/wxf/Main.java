package com.wxf;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

public class Main {

    @Data
    static class Test{

        private Map contribution;
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "\"contribution\":{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\",\"key4\":\"value4\"}\n" +
                "}";

        Test map = JSONObject.parseObject(str, Test.class);
        System.out.println(map.toString());

    }
}
