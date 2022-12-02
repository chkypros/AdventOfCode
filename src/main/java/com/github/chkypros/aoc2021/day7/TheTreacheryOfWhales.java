package com.github.chkypros.aoc2021.day7;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TheTreacheryOfWhales extends AbstractSolution {
    private List<Integer> positions;

    @Override
    public Long solvePartOne(Stream<String> stream) {
        positions = stream.flatMap(s -> Arrays.stream(s.split(",")))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        // Find median to minimize number of submarines that have to move
        final int basePosition = positions.get(positions.size() / 2);
        return calculateLowestCost(Collections.singletonList(basePosition), this::calculateFuelCostConstant);
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        positions = stream.flatMap(s -> Arrays.stream(s.split(",")))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        // Find average to minimize distances submarines have to move
        final int basePosition = (int) positions.stream().mapToInt(Integer::intValue).average().orElse(0d);
        return calculateLowestCost(Arrays.asList(basePosition, basePosition + 1), this::calculateFuelCostIncremental);
    }

    private long calculateLowestCost(List<Integer> positionsToCheck, Function<Integer, Long> calculateFuelCost) {
        return positionsToCheck.stream()
                .map(pos -> new PositionWithCost(pos, calculateFuelCost.apply(pos)))
                .min((o1, o2) -> (int) (o1.cost - o2.cost))
                .map(positionWithCost -> positionWithCost.cost)
                .orElseThrow(() -> new IllegalArgumentException("Invalid positions to check " + positionsToCheck));
    }

    private long calculateFuelCostConstant(final int position) {
        return positions.stream()
                .mapToLong(p -> Math.abs(p - position))
                .sum();
    }

    private long calculateFuelCostIncremental(final int position) {
        return positions.stream()
                .mapToLong(p -> Math.abs(p - position))
                .flatMap(n -> LongStream.rangeClosed(1, n))
                .sum();
    }

    private Optional<PositionWithCost> findLowerCostPosition(PositionWithCost positionCost, int step, Function<Integer, Long> calculateFuelCost) {
        int positionToCheck = positionCost.position;

        PositionWithCost lowerCostPositionCost = null;
        long cost;
        while (isValidIndex(positionToCheck += step) && positionCost.cost > (cost = calculateFuelCost.apply(positionToCheck))) {
            lowerCostPositionCost = PositionWithCost.of(positionToCheck, cost);
        }

        return Optional.ofNullable(lowerCostPositionCost);
    }

    private boolean isValidIndex(int i) {
        return i >= 0 && i < positions.size();
    }

    private static class PositionWithCost {
        public final int position;
        public final long cost;

        public PositionWithCost(int position, long cost) {
            this.position = position;
            this.cost = cost;
        }

        public static PositionWithCost of(int position, long cost) {
            return new PositionWithCost(position, cost);
        }

        @Override
        public String toString() {
            return "[" + position + ", " + cost + ']';
        }
    }
}
