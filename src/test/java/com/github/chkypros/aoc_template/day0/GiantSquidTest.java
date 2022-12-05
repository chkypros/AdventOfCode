package com.github.chkypros.aoc_template.day0;

import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.*;

public class GiantSquidTest {
    private final GiantSquid giantSquid = new GiantSquid();

    @Test
    public void partOneSample() throws Exception {
        final Stream<String> stream = getSampleInput(this);

        final Long answer = giantSquid.solvePartOne(stream);

        checkAnswer(4512L, answer);
    }

    @Test
    public void partTwoSample() throws Exception {
        final Stream<String> stream = getSampleInput(this);

        final var answer = giantSquid.solvePartTwo(stream);

        checkAnswer(1924L, answer);
    }

    @Test
    public void solve() throws Exception {
        giantSquid.solve();
    }
}
