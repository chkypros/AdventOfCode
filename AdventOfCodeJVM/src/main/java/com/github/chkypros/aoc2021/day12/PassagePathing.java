package com.github.chkypros.aoc2021.day12;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassagePathing extends AbstractSolution {
    @Override
    public Long solvePartOne(Stream<String> stream) {
        final List<CaveConnection> caveConnections = stream
                .map(CaveConnection::of)
                .collect(Collectors.toList());
        final CavesMap cavesMap = CavesMap.of(caveConnections);

        return cavesMap.countPathsBetween("start", "end", false);
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final List<CaveConnection> caveConnections = stream
                .map(CaveConnection::of)
                .collect(Collectors.toList());
        final CavesMap cavesMap = CavesMap.of(caveConnections);

        return cavesMap.countPathsBetween("start", "end", true);
    }
}
