package org.example.java_basic.customer.domain.request;

public class QueryString {
    private final String key;
    private final String value;

    public QueryString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public boolean exist(String operand) {
        return this.key.equals(operand);
    }
}
