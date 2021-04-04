package com.wxf.inventory.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * Zookeeper连接
 *
 * @author WangXiaofan777
 * @since 2021-04-04 08:34:52
 */
public class ZookeeperSession {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private ZooKeeper zooKeeper;

    public ZookeeperSession() {
        // 去连接zookeeper server，创建会话的时候，是异步去进行的
        // 所以要给一个监听器，说告诉我们什么时候才是真正完成了跟zk server的连接
        try {
            this.zooKeeper = new ZooKeeper("redismaster:2181,redisnode1:2181,redisnode2:2181",
                    50000,
                    new ZookeeperWatcher());

            // 给一个状态CONNECTING，连接中
            System.out.println(zooKeeper.getState());

            try {
                // CountDownLatch
                // java多线程并发同步的一个工具类
                // 会传递进去一些数字，比如说1,2 ，3 都可以
                // 然后await()，如果数字不是0，那么久卡住，等待

                // 其他的线程可以调用countDown()，减1
                // 如果数字减到0，那么之前所有在await的线程，都会逃出阻塞的状态
                // 继续向下运行

                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ZooKeeper session established......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分布式锁
     *
     * @param productId
     */
    public void acquireDistributedLock(Long productId) {
        String path = "/product-lock-" + productId;
        try {
            this.zooKeeper.create(path, "".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("success to acquire lock for product[id=" + productId + "]");
        } catch (Exception e) {
            // 如果那个商品对应的锁的node，已经存在了，就是已经被别人加锁了，那么就这里就会报错
            // NodeExistsException
            int count = 0;
            while (true) {
                try {
                    Thread.currentThread().sleep(20);
                    this.zooKeeper.create(path, "".getBytes(),
                            ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    count++;
                    continue;
                }
                System.out.println("success to acquire lock for product[id=" + productId + "] after " + count + " times try......");
                break;
            }
        }

    }


    /**
     * 释放掉一个分布式锁
     *
     * @param productId
     */
    public void releaseDistributedLock(Long productId) {
        String path = "/product-lock-" + productId;
        try {
            this.zooKeeper.delete(path, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Zookeeper Watcher
     */
    private static class ZookeeperWatcher implements Watcher {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("Receive watched event: " + watchedEvent.getState());
            if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                countDownLatch.countDown();
            }
        }
    }


    /**
     * 单例
     */
    private static class ZookeeperInstance {
        private static ZookeeperSession instance;

        static {
            instance = new ZookeeperSession();
        }

        public static ZookeeperSession getInstance() {
            return instance;
        }
    }

    /**
     * 获取ZookeeperSession
     *
     * @return
     */
    public static ZookeeperSession getZookeeperSession() {
        return ZookeeperInstance.getInstance();
    }

    public static void main(String[] args) {
        new ZookeeperSession();
    }
}
