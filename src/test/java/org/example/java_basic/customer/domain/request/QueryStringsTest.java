package org.example.java_basic.customer.domain.request;

import org.example.java_basic.customer.domain.request.QueryStrings;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueryStringsTest {
    @Test
    void create() {
        QueryStrings queryStrings = new QueryStrings("operand1=11&operator=*&operand2=11");
        assertThat(queryStrings).isNotNull();
    }
}