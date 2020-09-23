package com.example.SpringBootApp.dao;


import com.example.SpringBootApp.model.Role;

public interface RoleDao {

    Role findRoleByRoleName(String role);
}
