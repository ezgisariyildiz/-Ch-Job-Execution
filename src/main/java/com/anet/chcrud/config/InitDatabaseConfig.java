package com.anet.chcrud.config;

import com.clickhouse.jdbc.ClickHouseDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@RequiredArgsConstructor
public class InitDatabaseConfig {

   private final ClickHouseDataSource clickHouseDataSource;

    @PostConstruct
    public void initDatabase() {
        Connection conn = null;
        try {
            conn = clickHouseDataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery("CREATE TABLE IF NOT EXISTS USERS (ID INT NOT NULL, NAME VARCHAR(255), SURNAME VARCHAR(255), PRIMARY KEY ID) ENGINE = MergeTree");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
