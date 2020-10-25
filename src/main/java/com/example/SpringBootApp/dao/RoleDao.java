package com.example.SpringBootApp.dao;


import com.example.SpringBootApp.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RoleDao {

    Role findRoleByRoleName(String role);
}
