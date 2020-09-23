package com.example.SpringBootApp.service;


import com.example.SpringBootApp.model.Role;

public interface RoleService {

    Role findRoleByRoleName(String role);

}
