package com.anet.chcrud.repository;

import com.anet.chcrud.model.User;
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
public class CustomUserRepositoryImpl implements UserRepository {

    private final ClickHouseDataSource clickHouseDataSource;

    @Override
    public User save(User user) {
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO USERS (ID, NAME, SURNAME) VALUES(?, ?, ?)");
            stmt.setLong(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.executeQuery();
            conn.close();
            return user;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User found = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                conn.close();
                return found;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public User updateUser(User user) {
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE USERS SET NAME = ?, SURNAME = ? WHERE ID = ?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setLong(3, user.getId());
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getByName(String name) {
        List<User> foundUsers = new ArrayList<>();
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE NAME = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User found = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                foundUsers.add(found);
            }
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return foundUsers;
    }

    public List<User> getBySurname(String surname) {
        List<User> foundUsers = new ArrayList<>();
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE SURNAME = ?");
            stmt.setString(1, surname);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User found = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                foundUsers.add(found);
            }
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return foundUsers;
    }

    @Override
    public void deleteById(Long id) {
        try {
            Connection conn = clickHouseDataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM USERS WHERE ID = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3));
                conn.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }


}
