package org.example.mvc.controller;


import org.example.mvc.library.annotation.RequestMapping;
import org.example.mvc.library.annotation.Controller;
import org.example.mvc.library.request.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {

        return "home";
    }
}
