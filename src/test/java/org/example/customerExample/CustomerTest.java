package org.example.customerExample;

import org.example.customerExample.domain.Cooking;
import org.example.customerExample.domain.Menu;
import org.example.customerExample.domain.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;


/**
 * 1, 도메인
 *     ㄴ 손님, 메뉴판, 요리사, 요리
 *
 */
public class CustomerTest {


    @DisplayName("메뉴이름에 해당하는 요리를 주문 한다.")
    @Test
    void orderTest() {
        Customer customer = new Customer();

        Menu menu = new Menu(
                List.of(
                        new MenuItem("만두", 5000),
                        new MenuItem("돈까스", 7000)
                )
        );

        Cooking cooking = new Cooking();

        assertThatCode(() -> customer.order("만두", menu, cooking))
                .doesNotThrowAnyException();
    }
}
