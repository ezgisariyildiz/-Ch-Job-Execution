package com.anet.chcrud.config;

import com.clickhouse.jdbc.ClickHouseDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.Properties;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ClickhouseDatasourceConfig {

    @Bean
    public ClickHouseDataSource clickHouseDataSource() {
        String url = "jdbc:ch:http://192.168.1.101:8124/default";
        Properties properties = new Properties();
        properties.setProperty("user", "default");
        properties.setProperty("password", "");
        ClickHouseDataSource dataSource = null;
        try {
            dataSource = new ClickHouseDataSource(url, properties);
        } catch (SQLException throwables) {
            log.error("Couldn't connect to database!");
        }
        return dataSource;
    }

}


