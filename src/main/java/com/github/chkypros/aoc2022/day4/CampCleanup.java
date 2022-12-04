package com.github.chkypros.aoc2022.day4;

import com.github.chkypros.aoc_template.AbstractSolution;
import com.github.chkypros.aoc_template.Pair;

import java.util.function.IntFunction;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CampCleanup extends AbstractSolution {
    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return stream
                .map(this::getRangesPair)
                .filter(p -> p.first.isSupersetOf(p.second) || p.second.isSupersetOf(p.first))
                .count();
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        return stream
                .map(this::getRangesPair)
                .filter(p -> p.first.isOverlappingWith(p.second))
                .count();
    }

    private Pair<Range, Range> getRangesPair(String description) {
        final var matcher = PATTERN.matcher(description);
        matcher.find();
        final IntFunction<Integer> getSection = (i) -> Integer.parseInt(matcher.group(i));
        return new Pair<>(
                new Range(getSection.apply(1), getSection.apply(2)),
                new Range(getSection.apply(3), getSection.apply(4))
                );
    }

    private record Range(int start, int end) {
        boolean isSupersetOf(Range other) {
            return start <= other.start && end >= other.end;
        }

        boolean isOverlappingWith(Range other) {
            return !(start > other.end || end < other.start);
        }
    }
}
