package com.github.chkypros.aoc2021.day1;

import org.junit.Test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SonarSweepTest {

    private final SonarSweep sonarSweep = new SonarSweep();

    @Test
    public void partOneSingleIncrement() {
        Stream<String> stream = LongStream.range(0, 2).mapToObj(String::valueOf);

        Long answer = sonarSweep.solvePartOne(stream);

        assertNotNull(answer);
        assertEquals(1L, answer.longValue());
    }

    @Test
    public void partTwoSingleWindow() {
        Stream<String> stream = LongStream.range(0, 3).mapToObj(String::valueOf);

        Long answer = sonarSweep.solvePartTwo(stream);

        assertNotNull(answer);
        assertEquals(0L, answer.longValue());
    }

    @Test
    public void partTwoSingleIncrement() {
        Stream<String> stream = LongStream.range(0, 4).mapToObj(String::valueOf);

        Long answer = sonarSweep.solvePartTwo(stream);

        assertNotNull(answer);
        assertEquals(1L, answer.longValue());
    }

    @Test
    public void solve() throws Exception {
        sonarSweep.solve();
    }
}
