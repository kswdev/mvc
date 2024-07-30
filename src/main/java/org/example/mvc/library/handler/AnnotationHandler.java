package org.example.mvc.library.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationHandler {
    private final Class<?> clazz
            ;
    private final Method targetMethod;

    public AnnotationHandler(Class<?> clazz, Method declaredMethod) {
        this.clazz = clazz;
        this.targetMethod = declaredMethod;
    }

    public String handle(HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        Object handler = declaredConstructor.newInstance();

        return (String) targetMethod.invoke(handler, request, response);
    }
}
