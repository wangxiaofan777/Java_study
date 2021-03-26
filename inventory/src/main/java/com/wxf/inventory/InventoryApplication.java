package com.wxf.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * 注解ServletComponentScan需要和WebListener配合使用
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.wxf.inventory.mapper")
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

   /*
    @Bean
    public DataSource dataSource() {
        return new  datasource
    }

    @Bean
    public SqlSessionFactory sessionFactory() {
        Configuration configuration =  new Configuration();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        sqlSessionFactory.se
        return sqlSessionFactory;
    }

    @Bean
    public Transaction transaction() {
        SpringManagedTransaction springManagedTransaction = new SpringManagedTransaction();
        return springManagedTransaction;
    }*/
}
