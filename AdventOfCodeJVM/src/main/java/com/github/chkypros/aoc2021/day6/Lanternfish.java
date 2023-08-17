package com.github.chkypros.aoc2021.day6;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lanternfish extends AbstractSolution {

    private final Map<Integer, Long> fishBredInDays = new HashMap<>();

    private final int daysOfBreeding;

    public Lanternfish(int daysOfBreeding) {
        this.daysOfBreeding = daysOfBreeding;
    }

    @Override
    public Long solvePartOne(Stream<String> stream) {
        final FishIncubator fishIncubator = new FishIncubator();
        final Set<FishBreedingTracker> schoolOfLanternfish = new HashSet<>();
        stream.flatMap(s -> Arrays.stream(s.split(",")))
                .map(Integer::parseInt)
                .forEach(i -> schoolOfLanternfish.add(new FishBreedingTracker(fishIncubator, i)));

        for (int i = 0; i < daysOfBreeding; i++) {
            schoolOfLanternfish.forEach(FishBreedingTracker::proceedBreed);
            schoolOfLanternfish.addAll(fishIncubator.getFishInIncubation());
        }

        return (long) schoolOfLanternfish.size();
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {

        return stream.flatMap(s -> Arrays.stream(s.split(",")))
                .map(Integer::parseInt)
                .map(i -> 6 - i) // Normalize
                .mapToLong(i -> calculateFish(daysOfBreeding + i))
                .sum();
    }

    /**
     * Calculates number of fish produced, from a fish on interval 6 (first breed after 7 days),
     * after the given number of days
     */
    private long calculateFish(final int daysOfBreedingToCheck) {
        if (fishBredInDays.containsKey(daysOfBreedingToCheck)) {
            return fishBredInDays.get(daysOfBreedingToCheck);
        }

        final long fishBred = 1L + IntStream.rangeClosed(1, daysOfBreedingToCheck / 7)
                .mapToLong(i -> calculateFish(daysOfBreedingToCheck - i * 7 - 2))
                .sum();
        fishBredInDays.put(daysOfBreedingToCheck, fishBred);

        return fishBred;
    }
}
