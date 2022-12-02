package com.github.chkypros.aoc2021.day5;

public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }
}
