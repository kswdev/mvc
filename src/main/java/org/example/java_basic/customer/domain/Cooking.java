package org.example.java_basic.customer.domain;

public class Cooking {

    public Cook makeCook(MenuItem menuItem) {
        return new Cook(menuItem);
    }
}
