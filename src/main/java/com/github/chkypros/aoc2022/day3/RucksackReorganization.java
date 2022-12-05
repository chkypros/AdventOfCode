package com.github.chkypros.aoc2022.day3;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RucksackReorganization extends AbstractSolution {
    @Override
    public Long solvePartOne(Stream<String> stream) {
        return stream
                .map(this::findCommonItemTypeInCompartments)
                .mapToLong(this::getItemTypePriority)
                .sum();
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final var strings = stream.toArray(String[]::new);
        return IntStream.range(0, strings.length / 3)
                .map(i -> i * 3)
                .mapToObj(i -> findCommonItemType(strings[i], strings[i + 1], strings[i + 2]))
                .mapToLong(this::getItemTypePriority)
                .sum();
    }

    private char findCommonItemTypeInCompartments(String rucksack) {
        int rucksackMidIndex = rucksack.length() / 2;
        final var firstCompartment = rucksack.substring(0, rucksackMidIndex);
        final var secondCompartment = rucksack.substring(rucksackMidIndex);

        return findCommonItemType(firstCompartment, secondCompartment);
    }

    private char findCommonItemType(String... searchArea) {
        final var innerSearchArea = Arrays.stream(searchArea)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        for (char[] chars : innerSearchArea) {
            Arrays.sort(chars);
        }

        int[] index = new int[searchArea.length];
        while (!areAllEqualAtCurrentIndexes(innerSearchArea, index)) {
            // This is controlled input, so we ignore the case of no common item, shh
            int minIndex = findIndexWithMinimumPriority(innerSearchArea, index);
            index[minIndex]++;
        }

        return innerSearchArea[0][index[0]];
    }

    private static int findIndexWithMinimumPriority(char[][] innerSearchArea, int[] index) {
        int minIndex = 0;
        for (int i = 1; i < index.length; i++) {
            if (innerSearchArea[i][index[i]] < innerSearchArea[minIndex][index[minIndex]]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private boolean areAllEqualAtCurrentIndexes(char[][] innerSearchArea, int[] index) {
        for (int i = 1; i < index.length; i++) {
            if (innerSearchArea[i - 1][index[i - 1]] != innerSearchArea[i][index[i]]) {
                return false;
            }
        }
        return true;
    }

    private long getItemTypePriority(char c) {
        return Character.isLowerCase(c)
                ? c - 'a' + 1
                : c - 'A' + 27;
    }
}
