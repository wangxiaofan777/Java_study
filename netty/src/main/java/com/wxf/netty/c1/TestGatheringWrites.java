package com.wxf.netty.c1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 集中写
 */
public class TestGatheringWrites {

    public static void main(String[] args) {
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("hello");

        try (FileChannel channel = new RandomAccessFile("word2.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[]{b1, b2, b3});
        } catch (IOException e) {
        }

    }
}
