package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.model.User;
import com.example.SpringBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        User user = userService.fingByUserName(getCurrentUsername());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRolesStr());
        return "admin";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

  /*  @GetMapping("/user")
    public String getUserPage() {
        return "user";
    }*/
}
