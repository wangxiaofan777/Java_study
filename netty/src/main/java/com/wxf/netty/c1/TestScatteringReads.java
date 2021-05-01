package com.wxf.netty.c1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 分散度
 */
public class TestScatteringReads {

    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("data2.txt", "r").getChannel()) {
            ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
            ByteBuffer b2 = StandardCharsets.UTF_8.encode("hello");
            ByteBuffer b3 = StandardCharsets.UTF_8.encode("hello");

            channel.read(new ByteBuffer[]{b1, b2, b3});
        } catch (IOException e) {
        }

    }
}
