package com.wxf.netty.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TestByteBufferExam {

    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(256);
        source.put("i am A\n".getBytes(StandardCharsets.UTF_8));
        source.put("i am B\ni am ".getBytes(StandardCharsets.UTF_8));
        source.put("C\n".getBytes(StandardCharsets.UTF_8));

        split(source);
    }

    private static void split(ByteBuffer source) {
        // 开启读模式
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                // 开启读模式
                target.flip();
                System.out.println(StandardCharsets.UTF_8.decode(target));
            }
        }
    }
}
