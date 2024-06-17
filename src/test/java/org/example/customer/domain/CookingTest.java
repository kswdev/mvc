package org.example.customer.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CookingTest {


    @DisplayName("요리를 한다..")
    @Test
    void createCook() {

        Cooking cooking = new Cooking();
        MenuItem menuItem = new MenuItem("만두", 5000);

        Cook cook = cooking.makeCook(menuItem);

        assertThat(cook).isEqualTo(new Cook("만두", 5000));
    }
}
