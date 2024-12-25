package com.daniel.game.scene;

import com.daniel.core.Level;
import com.daniel.core.Scene;
import com.daniel.core.model.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeScene extends Scene {

    public HomeScene(Camera camera, Level level, BufferedImage buffer) {
        super(camera, level, buffer);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, buffer.getHeight() / 2, buffer.getWidth(), buffer.getHeight());

        double posX = camera.getPosX();
        double posY = camera.getPosY();
        double dirX = camera.getDirX();
        double dirY = camera.getDirY();
        double planeX = camera.getPlaneX();
        double planeY = camera.getPlaneY();

        for (int x = 0; x < buffer.getWidth(); x++) {
            double cameraPixel = 2 * (x / (double)buffer.getWidth()) - 1;
            double rayDirX = dirX + planeX * cameraPixel;
            double rayDirY = dirY + planeY * cameraPixel;

            double deltaDistX = rayDirY == 0 ? 0 : Math.abs(1 / rayDirX);
            double deltaDistY = rayDirX == 0 ? 0 : Math.abs(1 / rayDirY);

            int mapPosX = (int) Math.floor(posX);
            int mapPosY = (int) Math.floor(posY);

            double distToSideX, distToSideY;
            int stepX, stepY;

            if (rayDirX < 0) {
                distToSideX = (posX - mapPosX) * deltaDistX;
                stepX = -1;
            } else {
                distToSideX = (mapPosX + 1 - posX) * deltaDistX;
                stepX = 1;
            }

            if (rayDirY < 0) {
                distToSideY = (posY - mapPosY) * deltaDistY;
                stepY = -1;
            } else {
                distToSideY = (mapPosY + 1 - posY) * deltaDistY;
                stepY = 1;
            }

            boolean isHit = false;
            int hitSide = 0;
            double distance = 0;

            while (!isHit) {
                if (distToSideX < distToSideY) {
                    distToSideX += deltaDistX;
                    mapPosX += stepX;
                    hitSide = 0;
                } else {
                    distToSideY += deltaDistY;
                    mapPosY += stepY;
                    hitSide = 1;
                }

                if (mapPosX < 0 || mapPosX >= level.getMap().length || mapPosY < 0 || mapPosY >= level.getMap()[0].length) {
                    isHit = true;
                } else if (level.getMap()[mapPosX][mapPosY] > 0) {
                    isHit = true;
                }
            }

            if (hitSide == 0) {
                distance = (mapPosX - posX + (1 - stepX) / 2) / rayDirX;
            } else {
                distance = (mapPosY - posY + (1 - stepY) / 2) / rayDirY;
            }

            double wallHeight = buffer.getHeight() / distance;
            int lineStart = (int)(buffer.getHeight() / 2 - wallHeight / 2);
            int lineEnd = (int)(buffer.getHeight() / 2 + wallHeight / 2);

            Color color;

            if (hitSide == 0) {
                color = new Color(255, 0, 0);
            } else {
                color = new Color(128, 0, 0);
            }

            g.setColor(color);
            g.drawLine(x, lineStart, x, lineEnd);
        }
    }
}
