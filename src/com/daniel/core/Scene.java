package com.daniel.core;

import com.daniel.core.base.Renderable;
import com.daniel.core.model.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Scene implements Renderable {

    protected final BufferedImage buffer;
    protected final Camera camera;
    protected final Level level;

    public Scene(Camera camera, Level level, BufferedImage buffer) {
        this.level = level;
        this.camera = camera;
        this.buffer = buffer;
    }

    @Override
    public abstract void render(Graphics2D g);


    public Level getLevel() {
        return level;
    }

    public Camera getCamera() {
        return camera;
    }

}
