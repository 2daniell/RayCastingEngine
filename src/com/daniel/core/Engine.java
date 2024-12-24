package com.daniel.core;

import com.daniel.core.location.Location;
import com.daniel.core.model.Camera;
import com.daniel.data.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Engine extends JFrame implements Runnable {

    private final BufferedImage buffer;
    private Scene scene;

    public Engine() {
        this.buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
    }

    public void render3D() {
        scene.render();
    }

    public void init() {
        if (scene == null) throw new RuntimeException("Scene is not set");

        new Thread(this).start();

        setFocusable(true);
        addKeyListener(scene.getCamera());
        setVisible(true);
    }

    @Override
    public void run() {
        render3D();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, this);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }
}
