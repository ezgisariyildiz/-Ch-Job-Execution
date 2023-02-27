package com.anet.chcrud.controller;


import com.anet.chcrud.model.User;
import com.anet.chcrud.service.UserService;
import com.clickhouse.jdbc.ClickHouseDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final ClickHouseDataSource clickHouseDataSource;

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        User saved = userService.save(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@RequestParam("id") Long id) {
        User found = userService.getById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUserById(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<User>> getByName(@RequestParam("name") String name) {
        List<User> foundUserList = userService.getByName(name);
        return new ResponseEntity<>(foundUserList, HttpStatus.OK);
    }

    @GetMapping("/surname")
    public ResponseEntity<List<User>> getBySurname(@RequestParam("surname") String surname) {
        List<User> foundUserList = userService.getBySurname(surname);
        return new ResponseEntity<>(foundUserList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "id") Long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/test")
    public void test() {
        try {
            Connection conn = clickHouseDataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from test");
            if (rs.next()) {
                log.info("############################" + rs.getString(1) + rs.getString(2) + rs.getString(3) + "##########################################");
            }
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

}
