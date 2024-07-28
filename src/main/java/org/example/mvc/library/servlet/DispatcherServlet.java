package org.example.mvc.library.servlet;

import org.example.mvc.controller.Controller;
import org.example.mvc.library.ModelAndView;
import org.example.mvc.library.handler.HandlerAdapter;
import org.example.mvc.library.handler.HandlerKey;
import org.example.mvc.library.handler.RequestMappingHandlerMapping;
import org.example.mvc.library.handler.SimpleControllerHandlerAdapter;
import org.example.mvc.library.request.RequestMethod;
import org.example.mvc.view.View;
import org.example.mvc.view.resolver.JspViewResolver;
import org.example.mvc.view.resolver.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping handlerMapping;
    private List<ViewResolver> viewResolvers;
    private List<HandlerAdapter> handlerAdapters;

    @Override
    public void init() {
         handlerMapping = new RequestMappingHandlerMapping();
         handlerMapping.init();

         handlerAdapters   = Collections.singletonList(new SimpleControllerHandlerAdapter());
         viewResolvers   = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) {
        log.info("[DispatcherServlet] service started.");
        log.info("[requestURL] : [{}]", req.getRequestURI());

        try {
            Controller handler = handlerMapping.findHandler(new HandlerKey(RequestMethod.valueOf(req.getMethod()), req.getRequestURI()));

            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(adapter -> adapter.support(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for [" + handler + "]"));

            ModelAndView modelAndView = handlerAdapter.handle(req, res, handler);

            for (ViewResolver resolver : viewResolvers) {
                View view = resolver.resolveView(modelAndView.getViewName());
                view.rander(modelAndView.getModel(), req, res);
            }

        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);
        }
        //super.service(req, res);
    }
}
