package com.github.chkypros.aoc2021.day1;


import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SonarSweep extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        Long increments = 0L;

        List<Long> depths = stream.map(Long::valueOf).collect(Collectors.toList());
        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i - 1)) {
                increments++;
            }
        }

        return increments;
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        Long increments = 0L;

        List<Long> depths = stream.map(Long::valueOf).collect(Collectors.toList());
        if (depths.size() < 4) return 0L;

        long sum = depths.get(0) + depths.get(1) + depths.get(2);
        for (int i = 3; i < depths.size(); i++) {
            final long nextSum = sum - depths.get(i - 3) + depths.get(i);
            if (nextSum > sum) {
                increments++;
            }
            sum = nextSum;
        }

        return increments;
    }
}
