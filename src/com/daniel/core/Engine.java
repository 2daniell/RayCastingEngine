package com.daniel.core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Engine extends JFrame implements Runnable {

    private final BufferedImage buffer;
    private Scene scene;

    private int currentFPS = 0;
    private boolean showFPS = false;

    public Engine() {
        this.buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
    }

    public void render3D() {
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        scene.render(g);

        if (showFPS) {
            g.setColor(Color.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            g.drawString("FPS: " + currentFPS, 10, 60);

        }
        g.dispose();
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
        boolean running = true;
        int FPS = 60;
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;

        int frames = 0;
        long lastTimer = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            frames++;

            if (delta >= 1) {
                render3D();
                repaint();
                delta--;
            }

            if (System.nanoTime() - lastTimer >= 1000000000) {
                currentFPS = frames;
                frames = 0;
                lastTimer = System.nanoTime();
            }

            System.out.println(currentFPS);

            try {
                long sleepTime = (long) (lastTime - System.nanoTime() + nsPerTick) / 1000000;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void showFPS() {
        showFPS = true;
    }

    public void hideFPS() {
        showFPS = false;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }
}
