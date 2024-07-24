package org.example.mvc;

import org.example.mvc.library.annotation.Controller;
import org.example.mvc.library.annotation.Service;
import org.example.mvc.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Contoller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        log.debug("beans [{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;

        log.debug(clazz.getName());

        log.debug("User all declared Fields : [{}]",
                Arrays.stream(clazz.getDeclaredFields())
                        .collect(Collectors.toList())
        );
        log.debug("User all declared Constructors : [{}]",
                Arrays.stream(clazz.getDeclaredConstructors())
                        .collect(Collectors.toList())
        );
        log.debug("User all declared Methods : [{}]",
                Arrays.stream(clazz.getDeclaredMethods())
                        .collect(Collectors.toList())
        );
    }

    /**
     * 힙 영역에 로드되어있는 클래스 객체 가져오는 3가지 방법
     */
    @Test
    void load() throws ClassNotFoundException {
        //1
        Class<User> clazz = User.class;
        //2
        User user = new User("userId", "name");
        Class<? extends User> clazz2 = user.getClass();
        //3
        Class<?> clazz3 = Class.forName("org.example.mvc.model.User");

        log.debug("clazz : [{}]", clazz);
        log.debug("clazz2 : [{}]", clazz2);
        log.debug("clazz3 : [{}]", clazz3);

        assertThat(clazz  == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();
    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example.mvc");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
