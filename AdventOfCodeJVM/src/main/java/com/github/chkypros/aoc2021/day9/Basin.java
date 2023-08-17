package com.github.chkypros.aoc2021.day9;

import com.github.chkypros.aoc_common.Point;

import java.util.HashSet;
import java.util.Set;

public class Basin {
    private final Set<Point> pointsInBasin;
    private final int[][] height;

    public Basin(final int[][] height, final Point basinLowPoint) {
        this.height = height;

        this.pointsInBasin = new HashSet<>();

        expandTowards(basinLowPoint);
    }

    private void expandTowards(final Point point) {
        if (isNotValidPoint(point) || 9 == heightAtPoint(point) || pointsInBasin.contains(point)) {
            return;
        }

        pointsInBasin.add(point);
        expandTowards(Point.of(point.row - 1, point.column));
        expandTowards(Point.of(point.row, point.column - 1));
        expandTowards(Point.of(point.row + 1, point.column));
        expandTowards(Point.of(point.row, point.column + 1));
    }

    private boolean isNotValidPoint(Point point) {
        return point.row < 0 || point.column < 0
                || point.row >= height.length || point.column >= height[0].length;
    }

    private int heightAtPoint(final Point point) {
        return height[point.row][point.column];
    }

    public Set<Point> getPoints() {
        return pointsInBasin;
    }
}
