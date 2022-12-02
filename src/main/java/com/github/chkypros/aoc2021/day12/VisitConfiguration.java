package com.github.chkypros.aoc2021.day12;

import java.util.Map;

public class VisitConfiguration {
    private final Map<String, Cave> caves;
    private final String endCave;
    private final boolean doubleVisit;

    public VisitConfiguration(Map<String, Cave> caves, String endCave, boolean doubleVisit) {
        this.caves = caves;
        this.endCave = endCave;
        this.doubleVisit = doubleVisit;
    }

    public Map<String, Cave> getCaves() {
        return caves;
    }

    public String getEndCave() {
        return endCave;
    }

    public boolean isDoubleVisit() {
        return doubleVisit;
    }

    public static VisitConfiguration of(Map<String, Cave> caves, String endCave, boolean doubleVisit) {
        return new VisitConfiguration(caves, endCave, doubleVisit);
    }
}
