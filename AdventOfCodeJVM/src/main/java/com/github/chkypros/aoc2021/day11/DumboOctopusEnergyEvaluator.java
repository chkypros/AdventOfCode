package com.github.chkypros.aoc2021.day11;

import com.github.chkypros.aoc_common.Point;

import java.util.HashSet;
import java.util.Set;

public class DumboOctopusEnergyEvaluator {
    private final int[][] energyLevels;

    private long totalFlashes = 0;

    public DumboOctopusEnergyEvaluator(int[][] energyLevels) {
        this.energyLevels = energyLevels;
    }

    public long getTotalFlashes() {
        return totalFlashes;
    }

    public void evaluateSteps(int steps) {
        for (int i = 0; i < steps; i++) {
            long stepFlashes = evaluateNextStep();
            totalFlashes += stepFlashes;
        }
    }

    public long findNextSynchronizedStep() {
        int step = 0;
        long stepFlashes;
        do {
            step++;
            stepFlashes = evaluateNextStep();
            totalFlashes += stepFlashes;
        } while (100 != stepFlashes);

        return step;
    }

    private long evaluateNextStep() {
        // Increase all by 1
        for (int i = 0; i < energyLevels.length; i++) {
            for (int j = 0; j < energyLevels[0].length; j++) {
                increaseLevelAt(Point.of(i, j));
            }
        }

        // Expand from all points that flashed
        final Set<Point> expandedPoints = new HashSet<>();
        for (int i = 0; i < energyLevels.length; i++) {
            for (int j = 0; j < energyLevels[0].length; j++) {
                final Point point = Point.of(i, j);
                if (9 < energyAtPoint(point) && !expandedPoints.contains(point)) {
                    expandedPoints.add(point);

                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            expandTowards(Point.of(point.row + k, point.column + l), expandedPoints);
                        }
                    }
                }
            }
        }

        long flashes = 0;
        // Reset energy levels of flashed
        for (int i = 0; i < energyLevels.length; i++) {
            for (int j = 0; j < energyLevels[0].length; j++) {
                final Point point = Point.of(i, j);
                if (9 < energyAtPoint(point)) {
                    flashes++;
                    resetLevelAt(point);
                }
            }
        }

        return flashes;
    }

    private void increaseLevelAt(Point point) {
        energyLevels[point.row][point.column]++;
    }
    private void resetLevelAt(Point point) {
        energyLevels[point.row][point.column] = 0;
    }

    private void expandTowards(final Point point, Set<Point> expandedPoints) {
        if (isNotValidPoint(point) || 9 < energyAtPoint(point)) {
            return;
        }

        expandedPoints.add(point);
        increaseLevelAt(point);

        if (9 < energyAtPoint(point)) {
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    expandTowards(Point.of(point.row + k, point.column + l), expandedPoints);
                }
            }
        }
    }

    private boolean isNotValidPoint(Point point) {
        return point.row < 0 || point.column < 0
                || point.row >= energyLevels.length || point.column >= energyLevels[0].length;
    }

    private int energyAtPoint(final Point point) {
        return energyLevels[point.row][point.column];
    }
}
