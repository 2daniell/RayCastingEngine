package com.daniel;

import com.daniel.core.Engine;
import com.daniel.core.Level;
import com.daniel.core.Scene;
import com.daniel.core.model.Camera;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine();

        Level level = new Level();

        int[][] map = new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        level.setMap(map);

        Camera camera = new Camera();

        Scene scene = new Scene(camera, level, engine.getBuffer());

        engine.setScene(scene);
        engine.showFPS();
        engine.init();

    }
}
