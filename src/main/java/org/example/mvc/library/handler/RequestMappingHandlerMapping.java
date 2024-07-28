package org.example.mvc.library.handler;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.UserListController;
import org.example.mvc.library.request.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    // key : [/user], value : [UserController]
    private final Map<HandlerKey, Controller> controllerMap = new HashMap<>();

    public void init() {
        controllerMap.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        controllerMap.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        controllerMap.put(new HandlerKey(RequestMethod.POST, "/users"), new UserListController());
    }

    public Controller findHandler(HandlerKey handlerKey) {
        return controllerMap.get(handlerKey);
    }
}
