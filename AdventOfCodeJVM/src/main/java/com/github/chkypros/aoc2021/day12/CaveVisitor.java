package com.github.chkypros.aoc2021.day12;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CaveVisitor {
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]+");
    private static final int UNSET_VISITS = Integer.MAX_VALUE;

    private final String startCave;
    private final VisitConfiguration visitConfiguration;
    private final Map<String, Integer> lowerCaseCavesAllowedVisits;

    private String doubleCave;

    private CaveVisitor(String startCave, String doubleCave, Map<String, Integer> lowerCaseCavesAllowedVisits, VisitConfiguration visitConfiguration) {
        this.startCave = startCave;
        this.doubleCave = doubleCave;
        this.visitConfiguration = visitConfiguration;
        this.lowerCaseCavesAllowedVisits = lowerCaseCavesAllowedVisits;
    }

    public static CaveVisitor of(String startCave, VisitConfiguration visitConfiguration) {
        final HashMap<String, Integer> lowerCaseCavesAllowedVisits = new HashMap<>();
        visitConfiguration.getCaves().keySet().stream()
                        .filter(cave -> !startCave.equals(cave) && !visitConfiguration.getEndCave().equals(cave))
                        .filter(cave -> LOWERCASE_PATTERN.matcher(cave).find())
                        .forEach(cave -> lowerCaseCavesAllowedVisits.put(cave, 1));
        lowerCaseCavesAllowedVisits.put(startCave, -1);
        return new CaveVisitor(startCave, null, lowerCaseCavesAllowedVisits, visitConfiguration);
    }

    private static CaveVisitor of(String startCave, String doubleCave, Map<String, Integer> visitedLowerCaseCaves, VisitConfiguration visitConfiguration) {
        return new CaveVisitor(startCave, doubleCave, new HashMap<>(visitedLowerCaseCaves), visitConfiguration);
    }

    public Long countPaths() {
        if (startCave.equals(visitConfiguration.getEndCave())) {
            return 1L;
        }

        if (LOWERCASE_PATTERN.matcher(startCave).find()) {
            if (lowerCaseCavesAllowedVisits.get(startCave) == 0) {
                doubleCave = startCave;
            }
            lowerCaseCavesAllowedVisits.merge(startCave, -1, Integer::sum);
        }

        return visitConfiguration.getCaves().get(startCave).getConnections().stream()
                .filter(cave ->
                        lowerCaseCavesAllowedVisits.getOrDefault(cave.getName(), UNSET_VISITS) > 0
                        || lowerCaseCavesAllowedVisits.get(cave.getName()) == 0 && visitConfiguration.isDoubleVisit() && null == doubleCave
                )
                .map(cave -> CaveVisitor.of(cave.getName(), doubleCave, lowerCaseCavesAllowedVisits, visitConfiguration))
                .mapToLong(CaveVisitor::countPaths)
                .sum();
    }
}
