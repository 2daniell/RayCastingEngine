package com.daniel.utils;

public final class MathUtils {

    public static double magnitude(double rayDirX, double rayDirY) {
        return Math.sqrt((rayDirX*rayDirX) + (rayDirY*rayDirY));
    }
}
