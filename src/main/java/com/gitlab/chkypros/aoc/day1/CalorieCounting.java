package com.gitlab.chkypros.aoc.day1;

import com.gitlab.chkypros.aoc_template.AbstractSolution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class CalorieCounting extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return solveForTop(stream, 1);
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        return solveForTop(stream, 3);
    }

    private static Long solveForTop(Stream<String> stream, int topElvesToFind) {
        List<List<Long>> elvesCalories = getPerElfCalories(stream);
        return elvesCalories.stream().map(elfCalories -> elfCalories.stream()
                        .mapToLong(Long::longValue)
                        .sum())
                .sorted(Comparator.reverseOrder())
                .mapToLong(Long::valueOf)
                .limit(topElvesToFind)
                .sum();
    }

    private static List<List<Long>> getPerElfCalories(Stream<String> stream) {
        var elfCalories = new ArrayList<List<Long>>();
        elfCalories.add(new ArrayList<>());
        var elfIndex = 0;

        for (String calories : stream.toList()) {
            if ("".equals(calories)) {
                elfIndex++;
                elfCalories.add(new ArrayList<>());
            } else {
                elfCalories.get(elfIndex).add(Long.parseLong(calories));
            }
        }

        return elfCalories;
    }
}
