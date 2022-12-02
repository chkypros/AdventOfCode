package com.github.chkypros.aoc2021.day13;

import com.github.chkypros.aoc_template.AbstractSolution;
import com.github.chkypros.aoc_template.Point;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

public class TransparentOrigami extends AbstractSolution {

    private static final String COORDINATES_SEPARATOR = ",";

    @Override
    public Long solvePartOne(Stream<String> stream) {
        final Set<Point> foldedDots = processInstructions(stream, true);
        return (long) foldedDots.size();
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        final Set<Point> foldedDots = processInstructions(stream, false);

        final int maxRow = foldedDots.stream()
                .mapToInt(Point::row)
                .max()
                .getAsInt();
        final int maxColumn = foldedDots.stream()
                .mapToInt(Point::column)
                .max()
                .getAsInt();

        char[][] plane = new char[maxRow + 1][maxColumn + 1];
        foldedDots.forEach(point -> plane[point.row][point.column] = '#');
        for (char[] chars : plane) {
            for (int j = 0; j < plane[0].length; j++) {
                final char c = '#' == chars[j] ? chars[j] : ' ';
                System.out.print(c);
            }
            System.out.println();
        }

        return (long) foldedDots.size();
    }

    private Set<Point> processInstructions(Stream<String> stream, boolean processOnlyFirstInstruction) {
        final Map<Boolean, List<String>> input = stream
                .filter(s -> !"".equals(s.trim()))
                .collect(partitioningBy(s -> s.contains(COORDINATES_SEPARATOR)));

        final Set<Point> dots = input.get(true).stream()
                .map(coordinates -> coordinates.split(COORDINATES_SEPARATOR))
                    .map(coordinates -> Point.of(Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[0])))
                .collect(Collectors.toSet());

        final List<FoldInstruction> instructions = input.get(false).stream()
                .map(FoldInstruction::of)
                .collect(Collectors.toList());

        final List<FoldInstruction> instructionsToProcess = processOnlyFirstInstruction ?
                Collections.singletonList(instructions.get(0))
                : instructions;
        return applyInstructions(instructionsToProcess, dots);
    }

    private Set<Point> applyInstructions(List<FoldInstruction> instructions, Set<Point> dots) {
        Set<Point> foldedDots = new HashSet<>(dots);

        for (FoldInstruction instruction : instructions) {
            foldedDots = applyInstruction(instruction, foldedDots);
        }

        return foldedDots;
    }

    private Set<Point> applyInstruction(FoldInstruction instruction, Set<Point> dots) {
        return dots.stream()
                .map(dot -> foldPoint(dot, instruction))
                .collect(Collectors.toSet());
    }

    private Point foldPoint(Point dot, FoldInstruction instruction) {
        int row;
        int column;

        if ("x".equals(instruction.getAxis())) {
            row = dot.row;
            column = instruction.getIndex() - Math.abs(dot.column - instruction.getIndex());
        } else {
            column = dot.column;
            row = instruction.getIndex() - Math.abs(dot.row - instruction.getIndex());
        }

        return Point.of(row, column);
    }
}
