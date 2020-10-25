package com.example.SpringBootApp.service;

import com.example.SpringBootApp.dao.RoleDao;
import com.example.SpringBootApp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {

        this.roleDao = roleDao;
    }

    @Override
    public Role findRoleByRoleName(String role) {
        return roleDao.findRoleByRoleName(role);
    }

}
