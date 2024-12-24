package com.daniel.core;

import com.daniel.core.base.Renderable;
import com.daniel.core.location.Location;
import com.daniel.core.model.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scene implements Renderable {

    private final BufferedImage buffer;
    private final Camera camera;
    private final Level level;

    public Scene(Camera camera, Level level, BufferedImage buffer) {
        this.level = level;
        this.camera = camera;
        this.buffer = buffer;
    }

    public Level getLevel() {
        return level;
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public void render() {

        int CENTER_SCREEN = buffer.getHeight() / 2;

        Graphics2D g = (Graphics2D) buffer.getGraphics();

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, buffer.getHeight() / 2, buffer.getWidth(), buffer.getHeight());
    }
}
