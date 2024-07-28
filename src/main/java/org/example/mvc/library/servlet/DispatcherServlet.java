package org.example.mvc.library.servlet;

import org.example.mvc.controller.Controller;
import org.example.mvc.library.handler.HandlerKey;
import org.example.mvc.library.handler.RequestMappingHandlerMapping;
import org.example.mvc.library.request.RequestMethod;
import org.example.mvc.view.View;
import org.example.mvc.view.resolver.JspViewResolver;
import org.example.mvc.view.resolver.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping handlerMapping;
    private List<ViewResolver> viewResolvers;

    @Override
    public void init() {
         handlerMapping = new RequestMappingHandlerMapping();
         handlerMapping.init();

         viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) {
        log.info("[DispatcherServlet] service started.");
        log.info("[requestURL] : [{}]", req.getRequestURI());

        try {
            Controller handler = handlerMapping.findHandler(new HandlerKey(RequestMethod.valueOf(req.getMethod()), req.getRequestURI()));
            String viewName = handler.handleRequest(req, res);

            for (ViewResolver resolver : viewResolvers) {
                View view = resolver.resolveView(viewName);
                view.rander(new HashMap<>(), req, res);
            }

        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);
        }
        //super.service(req, res);
    }
}
