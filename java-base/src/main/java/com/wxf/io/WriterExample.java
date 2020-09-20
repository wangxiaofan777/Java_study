package com.wxf.io;

import java.io.*;

/**
 * Writer示例
 *
 * @author WangMaoSong
 * @date 2020-09-20 18:55:25
 */
public class WriterExample {

    private static final String fileName = "D:\\java\\workspace\\Java_study\\java-base\\src\\main\\java\\com\\wxf\\io\\test.txt";

    public static void main(String[] args) {
        fileOutputWriter();
    }


    /**
     * FileOutputWriter示例
     */
    public static void fileOutputWriter() {
        FileOutputStream outputStream = null;
        Writer writer = null;
        try {
            outputStream = new FileOutputStream(fileName);
            writer = new OutputStreamWriter(outputStream);
            writer.write("Hello World~");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    /**
     * FileWriter示例
     */
    public static void fileWriter() {
        Writer writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write("Hello world!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
