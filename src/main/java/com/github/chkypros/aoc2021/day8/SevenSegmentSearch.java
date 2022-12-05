package com.github.chkypros.aoc2021.day8;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.github.chkypros.aoc2021.day8.ValueDecoder.*;

public class SevenSegmentSearch extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return stream.map(s -> s.split("\\|")[1])
                .flatMap((s -> Arrays.stream(s.split(" "))))
                .map(String::trim)
                .filter(s -> !"".equals(s))
                .filter(this::isUniquePattern)
                .count();
    }

    private boolean isUniquePattern(String pattern) {
        return isDigitOne(pattern)
                || isDigitFour(pattern)
                || isDigitSeven(pattern)
                || isDigitEight(pattern);
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        return stream.map(ValueDecoder::new)
                .mapToLong(ValueDecoder::decode)
                .sum();
    }
}
