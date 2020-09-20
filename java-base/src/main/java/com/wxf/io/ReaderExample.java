package com.wxf.io;

import java.io.*;

/**
 * Reader示例
 *
 * @author WangMaoSong
 * @date 2020-09-20 18:55:31
 */
public class ReaderExample {

    public static void main(String[] args) {
        fileInputStreamReader();
    }


    /**
     * FileInputStreamReader示例
     */
    public static void fileInputStreamReader() {
        FileInputStream inputStream = null;
        InputStreamReader reader = null;
        try {
            inputStream = new FileInputStream("D:\\java\\workspace\\Java_study\\java-base\\src\\main\\java\\com\\wxf\\io\\ReaderExample.java");
            reader = new InputStreamReader(inputStream);
            int data = reader.read();
            while (data != -1) {
                char c = (char) data;
                System.out.println(c);

                data = reader.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * FileReader示例
     */
    public static void fileReader() {
        Reader reader = null;
        try {
            reader = new FileReader("D:\\java\\workspace\\Java_study\\java-base\\src\\main\\java\\com\\wxf\\io\\ReaderExample.java");
            int data = 0;
            data = reader.read();
            while (data != -1) {
                char c = (char) data;
                System.out.println(c);
                data = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
