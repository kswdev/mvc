package org.example.dependency_injection.controller;

import org.example.dependency_injection.annotation.Controller;
import org.example.dependency_injection.annotation.Inject;
import org.example.dependency_injection.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return this.userService;
    }
}
