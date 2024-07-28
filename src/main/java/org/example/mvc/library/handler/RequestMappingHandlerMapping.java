package org.example.mvc.library.handler;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    // key : [/user], value : [UserController]
    private final Map<String, Controller> controllerMap = new HashMap<>();

    public void init() {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/users", new UserListController());
    }

    public Controller findHandler(String urlPath) {
        return controllerMap.get(urlPath);
    }
}
