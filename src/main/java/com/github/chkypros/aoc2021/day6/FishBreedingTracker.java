package com.github.chkypros.aoc2021.day6;

public class FishBreedingTracker {
    private static final int REGULAR_BREEDING_DURATION = 6;
    private static final int FIRST_BREEDING_DURATION = 8;

    private final FishIncubator fishIncubator;

    private int dayOfBreeding;

    public FishBreedingTracker(FishIncubator fishIncubator) {
        this.fishIncubator = fishIncubator;
        this.dayOfBreeding = FIRST_BREEDING_DURATION;
    }

    public FishBreedingTracker(FishIncubator fishIncubator, int dayOfBreeding) {
        this.fishIncubator = fishIncubator;
        this.dayOfBreeding = dayOfBreeding;
    }

    public void proceedBreed() {
        dayOfBreeding--;

        if (0 > dayOfBreeding) {
            dayOfBreeding = REGULAR_BREEDING_DURATION;

            fishIncubator.fishBorn();
        }
    }
}
