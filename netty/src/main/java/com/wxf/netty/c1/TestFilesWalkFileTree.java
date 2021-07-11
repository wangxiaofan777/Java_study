package com.wxf.netty.c1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 文件遍历
 *
 * @author WangMaoSong
 * @since 2021-07-11 14:08:06
 */
public class TestFilesWalkFileTree {

    public static void main(String[] args) {
        /*try {
            Files.delete(Paths.get("D:\\java\\visualvm_143"));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        try {
            Files.walkFileTree(Paths.get("D:\\java\\jdk1.8\\jdk1.8.0_181"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("=====> 进入" + dir);
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file);
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("退出" + dir);
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void m1() {
        try {
            AtomicInteger dirCount = new AtomicInteger(0);
            AtomicInteger fileCount = new AtomicInteger(0);

            Files.walkFileTree(Paths.get("D:\\java\\jdk1.8\\jdk1.8.0_181"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("======>" + dir);
                    fileCount.getAndIncrement();
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file);
                    dirCount.getAndIncrement();
                    return super.visitFile(file, attrs);
                }
            });

            System.out.println("dir count: " + dirCount);
            System.out.println("file count: " + fileCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
