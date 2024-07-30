package org.example.dependency_injection.utils;

import org.example.dependency_injection.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public abstract class BeanFactoryUtils {

    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        //@Inject 가 붙은 클래스의 생성자만 가져오
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));

        if (injectedConstructors.isEmpty()) return null;
        else return injectedConstructors.iterator().next();
    }
}
