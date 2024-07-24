package org.example.java_basic.customer;

import org.example.java_basic.customer.domain.Cook;
import org.example.java_basic.customer.domain.Cooking;
import org.example.java_basic.customer.domain.Menu;
import org.example.java_basic.customer.domain.MenuItem;

public class Customer {
    public Cook order(String name, Menu menu, Cooking cooking) {
        MenuItem menuItem = menu.choose(name);
        Cook cook = cooking.makeCook(menuItem);
        return cook;
    }
}
