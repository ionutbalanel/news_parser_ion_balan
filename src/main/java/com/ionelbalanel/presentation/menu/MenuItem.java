package com.ionelbalanel.presentation.menu;

public final class MenuItem {
    private final String name;
    private final MenuAction action;

    public MenuItem(String name, MenuAction action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public MenuAction getAction() {
        return action;
    }
}
