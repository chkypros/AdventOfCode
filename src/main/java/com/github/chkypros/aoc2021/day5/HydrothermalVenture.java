package com.github.chkypros.aoc2021.day5;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class HydrothermalVenture extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        final Map<Integer, Map<Integer, Integer>> ventDensity = new Hashtable<>();
        stream.map(VentLine::of)
                .filter(Objects::nonNull)
                .filter(line -> !line.isDiagonal())
                .forEach(ventLine -> updateVentDensity(ventLine, ventDensity));

        return ventDensity.values().stream()
                .flatMap(c -> c.values().stream())
                .filter(v -> v > 1)
                .count();
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final Map<Integer, Map<Integer, Integer>> ventDensity = new Hashtable<>();
        stream.map(VentLine::of)
                .filter(Objects::nonNull)
                .forEach(ventLine -> updateVentDensity(ventLine, ventDensity));

        return ventDensity.values().stream()
                .flatMap(c -> c.values().stream())
                .filter(v -> v > 1)
                .count();
    }

    private void updateVentDensity(VentLine ventLine, Map<Integer, Map<Integer, Integer>> ventDensity) {
        final int stepX = getStep(ventLine, Point::x);
        final int stepY = getStep(ventLine, Point::y);
        final Predicate<Integer> conditionX = getCondition(stepX, ventLine.getEnd().x);
        final Predicate<Integer> conditionY = getCondition(stepY, ventLine.getEnd().y);

        for (int i = ventLine.getStart().x, j = ventLine.getStart().y;
             conditionX.test(i) && conditionY.test(j);
             i += stepX, j += stepY) {

            final int fj = j;
            ventDensity.compute(
                    i,
                    (key, value) -> {
                        final Map<Integer, Integer> newValue = (null == value) ? new Hashtable<>() : value;
                        newValue.compute(
                                fj,
                                (secondaryKey, secondaryValue) -> (null == secondaryValue) ? 1 : secondaryValue + 1
                        );

                        return newValue;
                    }
            );
        }
    }

    private int getStep(VentLine ventLine, Function<Point, Integer> field) {
        final Integer startField = field.apply(ventLine.getStart());
        final Integer endField = field.apply(ventLine.getEnd());

        return (Objects.equals(startField, endField)) ? 0
                : (startField.compareTo(endField) < 0) ? 1 : -1;
    }

    private Predicate<Integer> getCondition(int step, int end) {
        return step > 0
                ? x -> x <= end
                : x -> x >= end;
    }
}
