package org.example.mvc.library.servlet;

import org.example.mvc.library.ModelAndView;
import org.example.mvc.library.handler.adpater.AnnotationHandlerAdapter;
import org.example.mvc.library.handler.adpater.HandlerAdapter;
import org.example.mvc.library.handler.adpater.SimpleControllerHandlerAdapter;
import org.example.mvc.library.handler.mapping.AnnotationHandlerMapping;
import org.example.mvc.library.handler.mapping.HandlerKey;
import org.example.mvc.library.handler.mapping.HandlerMapping;
import org.example.mvc.library.handler.mapping.RequestMappingHandlerMapping;
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
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private List<HandlerMapping> handlerMappings;
    private List<ViewResolver> viewResolvers;
    private List<HandlerAdapter> handlerAdapters;

    @Override
    public void init() {
        RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping();
        rmhm.init();
        ahm.init();


        this.handlerMappings = List.of(rmhm, ahm);
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());
        viewResolvers   = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) {
        log.info("[DispatcherServlet] service started.");

        String requestURI = req.getRequestURI();
        RequestMethod method = RequestMethod.valueOf(req.getMethod());

        log.info("[requestURL] : [{}]", requestURI);

        try {
            Object handler = handlerMappings.stream()
                    .filter(ha -> ha.findHandler(new HandlerKey(method, requestURI)) != null)
                    .map(ha -> ha.findHandler(new HandlerKey(method, requestURI)))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No handler for [" + requestURI + "]"));

            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(adapter -> adapter.support(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for [" + method+ ", " + handler + "]"));

            ModelAndView modelAndView = handlerAdapter.handle(req, res, handler);

            for (ViewResolver resolver : viewResolvers) {
                View view = resolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), req, res);
            }

        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);
        }
        //super.service(req, res);
    }
}
