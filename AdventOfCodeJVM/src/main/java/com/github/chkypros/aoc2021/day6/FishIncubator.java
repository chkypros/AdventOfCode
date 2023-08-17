package com.github.chkypros.aoc2021.day6;

import java.util.HashSet;
import java.util.Set;

public class FishIncubator {
    private final Set<FishBreedingTracker> fishInIncubation = new HashSet<>();

    public Set<FishBreedingTracker> getFishInIncubation() {
        final HashSet<FishBreedingTracker> fishFromIncubation = new HashSet<>(fishInIncubation);
        fishInIncubation.clear();
        return fishFromIncubation;
    }

    public void fishBorn() {
        fishInIncubation.add(new FishBreedingTracker(this));
    }
}
