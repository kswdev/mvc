package org.example.customerExample.domain;

public class Cooking {

    public Cook makeCook(MenuItem menuItem) {
        return new Cook(menuItem);
    }
}
