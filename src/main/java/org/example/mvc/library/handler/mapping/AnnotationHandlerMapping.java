package org.example.mvc.library.handler.mapping;

import org.example.mvc.library.annotation.Controller;
import org.example.mvc.library.annotation.RequestMapping;
import org.example.mvc.library.handler.AnnotationHandler;
import org.example.mvc.library.request.RequestMethod;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{

    private final Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();
    private final Object[] basePackage;

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void init() {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> clazzWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        clazzWithControllerAnnotation.forEach(clazz -> {
            Arrays.stream(clazz.getDeclaredMethods()).forEach((declaredMethod) -> {
                RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                Arrays.stream(getRequestMethods(requestMapping))
                        .forEach((requestMethod -> handlers.put(
                                new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod)
                        )
                ));
            });
        });
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
