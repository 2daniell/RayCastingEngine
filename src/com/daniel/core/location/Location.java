package com.daniel.core.location;

public class Location {

    private int locX, locY;

    public Location() {
        this(0, 0);
    }

    public Location(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public void setLoc(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }
}
