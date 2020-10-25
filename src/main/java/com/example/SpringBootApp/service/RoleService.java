package com.example.SpringBootApp.service;


import com.example.SpringBootApp.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RoleService {

    Role findRoleByRoleName(String role);

}
