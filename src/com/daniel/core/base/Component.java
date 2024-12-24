package com.daniel.core.base;

import com.daniel.core.location.Location;

import javax.swing.*;

public class Component extends JPanel {

    private final Location location;

    public Component() {
        this(new Location());
    }

    public Component(Location location) {
        this.location = location;
    }
}
