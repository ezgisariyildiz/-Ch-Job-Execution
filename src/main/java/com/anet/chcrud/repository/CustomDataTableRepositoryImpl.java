package com.anet.chcrud.repository;

import com.anet.chcrud.model.DataTable;
import com.clickhouse.jdbc.ClickHouseDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomDataTableRepositoryImpl implements DataTableRepository {

    private final ClickHouseDataSource clickHouseDataSource;

    @Override
    public List<DataTable> findAll() {
        List<DataTable> foundRecords = new ArrayList<>();
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM data_table");
            ResultSet rs = stmt.executeQuery();
            /*while (rs.next()) {
                DataTable found = new DataTable(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9), rs.getLong(10), rs.getLong(11), rs.getLong(12), rs.getLong(13), rs.getLong(14), rs.getLong(15), rs.getLong(16), rs.getLong(17), rs.getLong(18), rs.getLong(19), rs.getLong(20));
                foundRecords.add(found);
            }*/
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return foundRecords;
    }

    public List<DataTable> insertQuery() {
        List<DataTable> workInsert = new ArrayList<>();
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO data_table (col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (int i = 0; i < 50; i++) {
                // set parameter values for each insert query
                stmt.setLong(1, 1);
                stmt.setLong(2, 1);
                stmt.setLong(3, 1);
                stmt.setLong(4, 1);
                stmt.setLong(5, 1);
                stmt.setLong(6, 1);
                stmt.setLong(7, 1);

                stmt.setLong(8, 1);
                stmt.setLong(9, 1);
                stmt.setLong(10, 1);
                stmt.setLong(11, 1);
                stmt.setLong(12, 1);
                stmt.setLong(13, 1);
                stmt.setLong(14, 1);
                stmt.setLong(15, 1);
                stmt.setLong(16, 1);
                stmt.setLong(17, 1);
                stmt.setLong(18, 1);
                stmt.setLong(19, 1);
                stmt.setLong(20, 1);
                stmt.executeUpdate();
            }
        }   catch(SQLException e){
                log.error(e.getMessage());
            }
            return workInsert;

    }
   public List<DataTable> materializedView(){
        List<DataTable> createMv = new ArrayList<>();
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM mv_data");
            ResultSet rs = stmt.executeQuery();
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return createMv;
    }

}
