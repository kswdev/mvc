package org.example.mvc.library.handler.mapping;

import org.example.mvc.controller.*;
import org.example.mvc.library.request.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping{

    // key : [/user], value : [UserController]
    private final Map<HandlerKey, Controller> controllerMap = new HashMap<>();

    public void init() {
        //controllerMap.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        controllerMap.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        controllerMap.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
        controllerMap.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey) {
        return controllerMap.get(handlerKey);
    }
}
