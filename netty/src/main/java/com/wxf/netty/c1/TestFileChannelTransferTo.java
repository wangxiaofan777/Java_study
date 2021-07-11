package com.wxf.netty.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("to.txt").getChannel()) {


            // 效率高，底层会利用操作系统的零拷贝进行优化， 最大为2g
            long size = from.size();

//            from.transferTo(0, size, to);

            for (long left = size; left > 0; ) {
                left -= from.transferTo((size - left), left, to);
            }

        } catch (IOException e) {
        }
    }
}
