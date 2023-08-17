package com.github.chkypros.aoc2021.day3;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryDiagnostic extends AbstractSolution {
    @Override
    public Long solvePartOne(Stream<String> stream) {
        List<String> readings = stream.collect(Collectors.toList());
        final int numberOfReadings = readings.size();
        final int readingLength = readings.get(0).length();

        int[] oneCount = readings.stream()
                .map(String::toCharArray)
                .map(BinaryDiagnosticUtils::toIntArray)
                .reduce(new int[readingLength], BinaryDiagnosticUtils::sumArrays);

        final int halfNumberOfReadings = numberOfReadings / 2;
        int[] gammaRateArray = toRateArray(oneCount, c -> c > halfNumberOfReadings);
        int[] epsilonRateArray = toRateArray(oneCount, c -> c < halfNumberOfReadings || c == numberOfReadings);

        return (long) BinaryDiagnosticUtils.toRate(gammaRateArray) * BinaryDiagnosticUtils.toRate(epsilonRateArray);
    }

    private static int[] toRateArray(final int[] ones, final Predicate<Integer> oneSelector) {
        final int[] rateArray = new int[ones.length];
        for (int i = 0; i < ones.length; i++) {
            rateArray[i] = oneSelector.test(ones[i]) ? 1 : 0;
        }
        return rateArray;
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final List<int[]> readings = stream
                .map(String::toCharArray)
                .map(BinaryDiagnosticUtils::toIntArray)
                .collect(Collectors.toList());

        int[] oxygenGeneratorRateArray = findRateArray(
                readings,
                0,
                (ones, numberOfReadings) -> ones >= numberOfReadings - ones);
        int[] co2ScrubberRateArray = findRateArray(
                readings,
                0,
                (ones, numberOfReadings) -> ones < numberOfReadings - ones);

        final long oxygenGeneratorRate = BinaryDiagnosticUtils.toRate(oxygenGeneratorRateArray);
        final long co2ScrubberRate = BinaryDiagnosticUtils.toRate(co2ScrubberRateArray);

        return oxygenGeneratorRate * co2ScrubberRate;
    }

    private int[] findRateArray(List<int[]> readings, int index, BiPredicate<Integer, Integer> oneSelector) {
        final int numberOfReadings = readings.size();
        if (1 == numberOfReadings) {
            return readings.get(0);
        }

        final int onesCount = readings.stream()
                .mapToInt(r -> r[index])
                .sum();

        final int bit = oneSelector.test(onesCount, numberOfReadings) ? 1 : 0;

        final List<int[]> filteredReadings = readings.stream()
                .filter(r -> bit == r[index])
                .collect(Collectors.toList());

        return findRateArray(filteredReadings, index + 1, oneSelector);
    }
}
