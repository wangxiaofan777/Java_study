package com.wxf.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

	public static void main(String[] args) throws IOException {
		
		/**
		 * 1.RandomAccessFile读取文件
		 * 2.将数据通过Channel读取到Buffer
		 * 3.反转将数据再从Buffer中读出
		 */
		RandomAccessFile file = new RandomAccessFile("D:\\eclipse-workspace\\test\\src\\data\\nio-data.txt", "rw");
		FileChannel inChannel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buffer);
		while (bytesRead != -1) {
//			System.out.println((char) buffer.get());
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.println((char) buffer.get());
			}
			buffer.clear();
			
			bytesRead = inChannel.read(buffer);
		}

		file.close();
	}

}
