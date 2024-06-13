package org.example.customerExample;

import org.example.customerExample.domain.Cook;
import org.example.customerExample.domain.Cooking;
import org.example.customerExample.domain.Menu;
import org.example.customerExample.domain.MenuItem;

public class Customer {
    public Cook order(String name, Menu menu, Cooking cooking) {
        MenuItem menuItem = menu.choose(name);
        Cook cook = cooking.makeCook(menuItem);
        return cook;
    }
}
