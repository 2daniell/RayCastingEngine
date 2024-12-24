package com.daniel.core.location;

public class Location {

    private int x, y;

    public Location() {
        this(0, 0);
    }

    public Location(int locX, int locY) {
        this.x = locX;
        this.y = locY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLoc(int locX, int locY) {
        this.x = locX;
        this.y = locY;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locX=" + x +
                ", locY=" + y +
                '}';
    }
}
