package org.example.mvc.controller;

import org.example.mvc.library.annotation.Controller;
import org.example.mvc.library.annotation.RequestMapping;
import org.example.mvc.library.request.RequestMethod;

@Controller
public class HealtCheckController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String home() {
        return "ok";
    }
}
