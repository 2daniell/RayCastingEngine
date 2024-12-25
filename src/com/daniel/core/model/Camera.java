package com.daniel.core.model;

import com.sun.security.jgss.GSSUtil;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {

    private static final double FOV_FACTOR = 0.66;

    private static final double MOVE_SPEED = 0.2;
    private static final double ROTATE_SPEED = 0.08;

    private double posX, posY;
    private double dirX, dirY;
    private double planeX, planeY;
    public Camera() {
        this.planeX = 0.66;
        this.planeY = 0;
        this.dirX = 0;
        this.dirY = -1;
        this.posX = 5;
        this.posY = 5;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            posX += dirX * MOVE_SPEED;
            posY += dirY * MOVE_SPEED;
        }
        if (key == KeyEvent.VK_S) {
            posX -= dirX * MOVE_SPEED;
            posY -= dirY * MOVE_SPEED;
        }
        if (key == KeyEvent.VK_D) {
            double oldDirX = dirX;
            dirX = dirX * Math.cos(ROTATE_SPEED) - dirY * Math.sin(ROTATE_SPEED);
            dirY = oldDirX * Math.sin(ROTATE_SPEED) + dirY * Math.cos(ROTATE_SPEED);

            double oldPlaneX = planeX;
            planeX = planeX * Math.cos(ROTATE_SPEED) - planeY * Math.sin(ROTATE_SPEED);
            planeY = oldPlaneX * Math.sin(ROTATE_SPEED) + planeY * Math.cos(ROTATE_SPEED);
        }
        if (key == KeyEvent.VK_A) {
            double oldDirX = dirX;
            dirX = dirX * Math.cos(-ROTATE_SPEED) - dirY * Math.sin(-ROTATE_SPEED);
            dirY = oldDirX * Math.sin(-ROTATE_SPEED) + dirY * Math.cos(-ROTATE_SPEED);

            double oldPlaneX = planeX;
            planeX = planeX * Math.cos(-ROTATE_SPEED) - planeY * Math.sin(-ROTATE_SPEED);
            planeY = oldPlaneX * Math.sin(-ROTATE_SPEED) + planeY * Math.cos(-ROTATE_SPEED);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getDirX() {
        return dirX;
    }

    public double getDirY() {
        return dirY;
    }

    public double getPlaneX() {
        return planeX;
    }

    public double getPlaneY() {
        return planeY;
    }
}
