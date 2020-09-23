package com.example.SpringBootApp.service;

import com.example.SpringBootApp.model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(Long id);
    User fingByUserName(String username);
}
