package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.model.Role;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.service.RoleService;
import com.example.SpringBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return userService.listUsers();
    }

    @GetMapping("/get_auth_user")
    public User getAuthUser(){
        return userService.fingByUserName(getCurrentUsername());
    }

    @GetMapping("/admin/user")
    public User getUserByUsername(@RequestParam(value = "username") String username) {
        return userService.fingByUserName(username);
    }

    @PostMapping("/admin/user")
    public void addUser(User user,
                        @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
                        @RequestParam(value = "roleUser", required = false) String roleUser) {

        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null) {
            roles.add(roleService.findRoleByRoleName(roleAdmin));
        }
        if (roleUser != null) {
            roles.add(roleService.findRoleByRoleName(roleUser));
        }
        user.setRoles(roles);
        userService.add(user);
    }

    @PutMapping("/admin/user")
    public void editUser(User user,
                         @RequestParam(value = "roleAdmin", required = false) String roleAdmin,
                         @RequestParam(value = "roleUser", required = false) String roleUser) {

        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null) {
            roles.add(roleService.findRoleByRoleName(roleAdmin));
        }
        if (roleUser != null) {
            roles.add(roleService.findRoleByRoleName(roleUser));
        }
        user.setRoles(roles);
        userService.edit(user);
    }

    @DeleteMapping(path = "/admin/user")
    public void deleteUserById(@RequestParam("id") Long id) {
        userService.delete(userService.getById(id));
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
