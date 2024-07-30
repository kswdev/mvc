package org.example.mvc.controller;

import org.example.mvc.library.annotation.RequestMapping;
import org.example.mvc.library.request.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@org.example.mvc.library.annotation.Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }
}
