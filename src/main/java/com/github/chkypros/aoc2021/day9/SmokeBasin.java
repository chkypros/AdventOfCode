package com.github.chkypros.aoc2021.day9;

import com.github.chkypros.aoc_template.AbstractSolution;
import com.github.chkypros.aoc_template.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SmokeBasin extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        final List<String> heightsList = stream.collect(Collectors.toList());
        final int rows = heightsList.size();
        final int columns = heightsList.get(0).length();
        final int[][] height = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                height[i][j] = Integer.parseInt(heightsList.get(i).substring(j, j + 1));
            }
        }

        long riskLevel = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (isLowPoint(i, j, height)) {
                    riskLevel += height[i][j] + 1;
                }
            }
        }

        return riskLevel;
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final List<String> heightsList = stream.collect(Collectors.toList());
        final int rows = heightsList.size();
        final int columns = heightsList.get(0).length();
        final int[][] height = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                height[i][j] = Integer.parseInt(heightsList.get(i).substring(j, j + 1));
            }
        }

        final List<Basin> basins = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (isLowPoint(i, j, height)) {
                    basins.add(new Basin(height, Point.of(i, j)));
                }
            }
        }

        return basins.stream()
                .map(b -> (long) b.getPoints().size())
                .sorted((b1, b2) -> Long.compare(b2, b1))
                .limit(3)
                .reduce((b1, b2) -> b1 * b2)
                .get();
    }

    private boolean isLowPoint(int i, int j, int[][] height) {
        return !(isLowerPoint(i - 1, j, height[i][j], height)
                || isLowerPoint(i, j - 1, height[i][j], height)
                || isLowerPoint(i + 1, j, height[i][j], height)
                || isLowerPoint(i, j + 1, height[i][j], height));
    }

    private boolean isLowerPoint(int i, int j, int heightToCheck, int[][] height) {
        if (i < 0 || j < 0 || i >= height.length || j >= height[0].length) {
            return false;
        }

        return height[i][j] <= heightToCheck;
    }
}
