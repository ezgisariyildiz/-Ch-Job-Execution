package com.anet.chcrud.service;

import com.anet.chcrud.model.User;
import com.anet.chcrud.repository.CustomUserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final CustomUserRepositoryImpl userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public List<User> getByName(String name) {
        return userRepository.getByName(name);
    }

    public List<User> getBySurname(String surname) {
        return userRepository.getBySurname(surname);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    private final DataTableService dataTableService;

}
