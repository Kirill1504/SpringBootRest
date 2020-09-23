package com.example.SpringBootApp.dao;

import com.example.SpringBootApp.model.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(Long id);
    User fingByUserName(String username);

}
