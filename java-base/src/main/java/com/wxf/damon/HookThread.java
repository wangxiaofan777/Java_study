package com.wxf.damon;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hook测试类
 *
 * @author WangXiaofan777
 * @since 2020-11-03 14:28:27
 */
public class HookThread {

    public static void main(String[] args) {
        Path path = Paths.get("./", ".lock");
        if (!path.toFile().exists())
            throw new RuntimeException("this process has existed !!!");
        try {
            path.toFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            path.toFile().delete();
        }));
    }

}
