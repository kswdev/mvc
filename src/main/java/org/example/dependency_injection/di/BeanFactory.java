package org.example.dependency_injection.di;

import org.example.dependency_injection.utils.BeanFactoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Logger log = LoggerFactory.getLogger(BeanFactory.class);

    private final Set<Class<?>> preInstantiatedClazz;
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    private Object createInstance(Class<?> clazz) {
        //생성자
        Constructor<?> constructor = getInjectedConstructor(clazz);

        //파라미터
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            parameters.add(getParameterByClass(typeClass));
        }
        //인스턴스 생성 로직
        try {
            log.info("parameters : [{}]", parameters);
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> getInjectedConstructor(Class<?> clazz) {
        log.info("class : [{}]", clazz);
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);

        if (Objects.nonNull(constructor))
            return constructor;

        return clazz.getConstructors()[0];
    }
    private Object getParameterByClass(Class<?> typeClass) {
        Object instance = getBean(typeClass);

        log.info("class : {}", typeClass);

        if (Objects.nonNull(instance))
            return instance;

        return createInstance(typeClass);
    }


    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }
}
