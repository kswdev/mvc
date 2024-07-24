package org.example.java_basic.customer.domain.request;

import org.example.java_basic.customer.domain.request.QueryString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest {

    @Test
    void create() {
        QueryString queryString = new QueryString("operand", "11");
        assertThat(queryString).isNotNull();
    }
}
