package com.wxf.netty.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * String字符串与ByteBuffer互转
 */
public class TestString2ByteBuffer {

    public static void main(String[] args) {
        // 1.
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("Hello World".getBytes(StandardCharsets.UTF_8));

        // 2.encode
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("Hello World");


        // 3.wrap
        ByteBuffer buffer2 = ByteBuffer.wrap("Hello World".getBytes(StandardCharsets.UTF_8));

        // 第一种方式必须切换到写模式
        buffer.flip();
        System.out.println(StandardCharsets.UTF_8.decode(buffer));
        System.out.println(StandardCharsets.UTF_8.decode(buffer1));
        System.out.println(StandardCharsets.UTF_8.decode(buffer2));

    }
}
