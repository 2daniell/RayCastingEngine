package com.daniel.core;

import com.daniel.core.model.Camera;

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

        renderMinimap(g);

        g.dispose();
    }

    public void init() {
        if (scene == null) throw new RuntimeException("Scene is not set");

        new Thread(this).start();

        setFocusable(true);
        addKeyListener(scene.getCamera());
        addMouseMotionListener(scene.getCamera());
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

    private void renderMinimap(Graphics2D g) {

        int[][] map = scene.getLevel().getMap();

        int mapSize = 100;
        int tileSize = mapSize / map.length;
        int offsetX = buffer.getWidth() - mapSize - 10;
        int offsetY = 50;

        g.setColor(Color.BLACK);
        g.fillRect(offsetX, offsetY, mapSize, mapSize);

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] > 0) {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(offsetX + x * tileSize, offsetY + y * tileSize, tileSize, tileSize);
            }
        }

        drawPlayer(offsetX, offsetY, tileSize, g);
    }

    private void drawPlayer(int offsetX, int offsetY, int tileSize, Graphics2D g2d) {
        int cameraX = (int) (offsetX + scene.getCamera().getPosX() * tileSize);
        int cameraY = (int) (offsetY + scene.getCamera().getPosX() * tileSize);
        g2d.setColor(Color.RED);
        g2d.fillOval(cameraX - 3, cameraY - 3, 6, 6);

        drawRays(g2d, cameraX, cameraY, offsetX, offsetY, tileSize);
    }

    private void drawRays(Graphics2D g, int cameraX, int cameraY, int offsetX, int offsetY, int tileSize) {
        g.setColor(Color.YELLOW);

        int[][] map = scene.getLevel().getMap();

        for (int x = 0; x < buffer.getWidth(); x+=10) {
            double rayAngle = (scene.getCamera().getCameraAngle() - Math.PI / 6) + (x * (Math.PI / 3) / buffer.getWidth());
            double distanceToWall = 0;
            boolean hitWall = false;

            double eyeX = Math.cos(rayAngle);
            double eyeY = Math.sin(rayAngle);

            while (!hitWall && distanceToWall < 16) {
                distanceToWall += 0.1;

                int testX = (int)(scene.getCamera().getPosX() + eyeX * distanceToWall);
                int testY = (int)(scene.getCamera().getPosY() + eyeY * distanceToWall);

                if (testX < 0 || testX >= map[0].length || testY < 0 || testY >= map.length) {
                    hitWall = true;
                } else if (map[testY][testX] > 0) {
                    hitWall = true;
                }
            }

            int endX = (int) (scene.getCamera().getPosX() + eyeX * distanceToWall);
            int endY = (int) (scene.getCamera().getPosY() + eyeY * distanceToWall);
            g.drawLine(cameraX, cameraY, offsetX + endX * tileSize, offsetY + endY * tileSize);
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
