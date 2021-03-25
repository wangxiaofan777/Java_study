package com.wxf.inventory;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.activation.DataSource;


@SpringBootApplication
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
