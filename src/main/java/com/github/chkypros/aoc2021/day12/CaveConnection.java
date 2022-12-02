package com.github.chkypros.aoc2021.day12;

public class CaveConnection {

    private final String firstCaveName;
    private final String secondCaveName;

    public CaveConnection(String firstCaveName, String secondCaveName) {
        this.firstCaveName = firstCaveName;
        this.secondCaveName = secondCaveName;
    }

    public String getFirstCaveName() {
        return firstCaveName;
    }

    public String getSecondCaveName() {
        return secondCaveName;
    }

    public static CaveConnection of(String string) {
        return new CaveConnection(
                string.substring(0, string.indexOf("-")),
                string.substring(1 + string.indexOf("-")));
    }
}
