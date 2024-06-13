package org.example.customerExample.domain;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItem choose(String name) {
        return this.menuItems.stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴 이름입니다."));
    }
}
