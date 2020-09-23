package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.model.Role;
import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.service.RoleService;
import com.example.SpringBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = {"/users", "/"})
    public String printUsers(Model model) {
        List<User> userList = userService.listUsers();
        User user = userService.fingByUserName(getCurrentUsername());
        model.addAttribute("roles", user.getRolesStr());
        model.addAttribute("user", user);
        model.addAttribute("users", userList);
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "users";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user,
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
        return "redirect:/admin/users";
    }

    @PostMapping("/update")
    public String editUser(@ModelAttribute("user") User user,
                           HttpServletRequest request) {

        Set<Role> roles = user.getRoles();
        String roleUser = request.getParameter("ROLE_USER");
        String roleAdmin = request.getParameter("ROLE_ADMIN");
        if (roleAdmin != null) {
            roles.add(roleService.findRoleByRoleName(roleAdmin));
        }
        if (roleUser != null) {
            roles.add(roleService.findRoleByRoleName(roleUser));
        }
        user.setRoles(roles);
        userService.edit(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id){
        userService.delete(userService.getById(id));

        return "redirect:/admin/users";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}

