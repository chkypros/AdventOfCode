package com.github.chkypros.aoc2021.day20;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrenchMap extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        final List<String> input = stream.collect(Collectors.toList());
        final String algorithm = input.get(0);
        final List<String> imageLines = input.subList(2, input.size());

        return (long) imageLines.size();
    }
}
