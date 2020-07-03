package com.wxf.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

	public static void main(String[] args) throws IOException {

		/**
		 * 1.写入数据到Buffer
		 * 2.调用flip()方法
		 * 3.从Buffer中读取数据
		 */
		RandomAccessFile file = new RandomAccessFile("E:\\Java_study\\java-base\\src\\main\\java\\data\\nio-data.txt", "rw");
		FileChannel inChannel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buffer);
		while (bytesRead != -1) {
//			System.out.println((char) buffer.get());
			//将写模式切换为读模式
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.println((char) buffer.get());
			}
			//情况缓冲区，确保Buffer可以被再次写入
			buffer.clear();

			bytesRead = inChannel.read(buffer);
		}

		file.close();
	}

}
