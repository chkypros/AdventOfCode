package com.github.chkypros.aoc2021.day5;

import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;
import static com.github.chkypros.aoc_template.TestUtils.getSampleInput;

public class HydrothermalVentureTest {
    private final HydrothermalVenture hydrothermalVenture = new HydrothermalVenture();

    @Test
    public void solvePartOneSample() throws Exception {
        Stream<String> stream = getSampleInput(this);

        final Long answer = hydrothermalVenture.solvePartOne(stream);

        checkAnswer(5L, answer);
    }

    @Test
    public void solvePartTwoSample() throws Exception {
        Stream<String> stream = getSampleInput(this);

        final Long answer = hydrothermalVenture.solvePartTwo(stream);

        checkAnswer(12L, answer);
    }

    @Test
    public void solve() throws Exception {
        hydrothermalVenture.solve();
    }
}
