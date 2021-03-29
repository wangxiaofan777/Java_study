package com.wxf.inventory.listener;

import com.wxf.inventory.thread.RequestProcessorThreadPool;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 注册监听器
 *
 * @author WangXiaofan777
 * @since 2021-03-25 22:36:44
 */
@Slf4j
@WebListener
public class InitServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.warn("=================初始化监听器===================");
        log.warn("=================初始化内存队列和线程池===================");
        RequestProcessorThreadPool.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
