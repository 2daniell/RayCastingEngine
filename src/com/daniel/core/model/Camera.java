package com.daniel.core.model;

import com.daniel.core.location.Location;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {

    private double angle;
    private double moveSpeed = 1.0;
    private double rotationSpeed = Math.PI / 32;
    private Location location;

    public Camera() {
        this.angle = Math.PI / 2;
        this.location = new Location();
    }

    public double getAngle() {
        return angle;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            location.setX((int)(location.getX() + Math.cos(angle) * moveSpeed));
            location.setY((int)(location.getY() + Math.sin(angle) * moveSpeed));
        } else if (key == KeyEvent.VK_S) {
            location.setX((int)(location.getX() - Math.cos(angle) * moveSpeed));
            location.setY((int)(location.getY() - Math.sin(angle) * moveSpeed));
        }

        if (key == KeyEvent.VK_A) {
            location.setX((int)(location.getX() - Math.sin(angle) * moveSpeed));
            location.setY((int)(location.getY() + Math.cos(angle) * moveSpeed));
        } else if (key == KeyEvent.VK_D) {
            location.setX((int)(location.getX() + Math.sin(angle) * moveSpeed));
            location.setY((int)(location.getY() - Math.cos(angle) * moveSpeed));
        }

        if (key == KeyEvent.VK_LEFT) {
            angle -= rotationSpeed;
        } else if (key == KeyEvent.VK_RIGHT) {
            angle += rotationSpeed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
