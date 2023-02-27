package com.anet.chcrud.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClickhouseMySQLConfig {
    public static void main(String[] args) {
        try {
            Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

            // Bağlantı nesnesini oluştur
            Connection connection = DriverManager.getConnection("jdbc:clickhouse://127.0.0.1:9004/default", "default", "");
            // Sorgu çalıştırma nesnesi oluştur
            Statement statement = connection.createStatement();
            // Veritabanındaki verileri sorgula
            ResultSet resultSet = statement.executeQuery("SELECT * FROM table");
            // Sorgu sonuçlarını yazdır
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
            // Bağlantıyı kapat
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
