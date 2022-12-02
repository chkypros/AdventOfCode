package com.github.chkypros.aoc2021.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CavesMap {
    private final Map<String, Cave> caves = new HashMap<>();

    public CavesMap(List<CaveConnection> caveConnections) {
        caveConnections.forEach(this::addConnection);
    }

    private void addConnection(CaveConnection caveConnection) {
        final Cave firstCave = caves.computeIfAbsent(
                caveConnection.getFirstCaveName(),
                Cave::new
        );
        final Cave secondCave = caves.computeIfAbsent(
                caveConnection.getSecondCaveName(),
                Cave::new
        );

        firstCave.addConnection(secondCave);
        secondCave.addConnection(firstCave);
    }

    public static CavesMap of(List<CaveConnection> caveConnections) {
        return new CavesMap(caveConnections);
    }

    public Long countPathsBetween(String startCave, String endCave, boolean doubleVisit) {
        VisitConfiguration visitConfiguration = VisitConfiguration.of(caves, endCave, doubleVisit);
        final CaveVisitor caveVisitor = CaveVisitor.of(startCave, visitConfiguration);
        return caveVisitor.countPaths();
    }
}
