package org.example.java_basic.customer.domain;

import org.example.java_basic.customer.domain.Menu;
import org.example.java_basic.customer.domain.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuTest {

    @DisplayName("메뉴 이름에 해당하는 메뉴를 반환한다.")
    @Test
    void chooseValidTest() {

        Menu menu = new Menu(
                List.of(
                    new MenuItem("만두", 5000),
                    new MenuItem("돈까스", 7000)
                )
        );

        MenuItem menuItem = menu.choose("만두");
        assertThat(menuItem).isEqualTo(new MenuItem("만두", 5000));
    }

    @DisplayName("없는 메뉴이름에 에러를 반환한다.")
    @Test
    void chooseInvalidTest() {

        Menu menu = new Menu(
                 List.of(
                        new MenuItem("만두", 5000),
                        new MenuItem("돈까스", 7000)
                )
        );

        assertThatCode(() -> menu.choose("뚝"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 메뉴 이름입니다.");
    }
}
