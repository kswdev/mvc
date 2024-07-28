package org.example.mvc.library.servlet;

import org.example.mvc.controller.Controller;
import org.example.mvc.library.handler.HandlerKey;
import org.example.mvc.library.handler.RequestMappingHandlerMapping;
import org.example.mvc.library.request.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public void init() {
         handlerMapping = new RequestMappingHandlerMapping();
         handlerMapping.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) {
        log.info("[DispatcherServlet] service started.");
        log.info("[requestURL] : [{}]", req.getRequestURI());

        try {
            Controller handler = handlerMapping.findHandler(new HandlerKey(RequestMethod.valueOf(req.getMethod()), req.getRequestURI()));
            String viewName = handler.handleRequest(req, res);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/"+viewName);
            requestDispatcher.forward(req, res);
            System.out.println("forward!");
        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);
        }
        //super.service(req, res);
    }
}
