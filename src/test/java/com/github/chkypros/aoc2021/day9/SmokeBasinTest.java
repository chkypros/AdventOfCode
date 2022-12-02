package com.github.chkypros.aoc2021.day9;

import org.junit.Test;

import java.nio.file.Files;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;
import static com.github.chkypros.aoc_template.TestUtils.getSampleInput;
import static com.github.chkypros.aoc_template.Utils.getInputFilePath;

public class SmokeBasinTest {

    private final SmokeBasin smokeBasin = new SmokeBasin();

    @Test
    public void solvePartOneSample() throws Exception {
        Stream<String> stream = getSampleInput(this);

        final Long answer = smokeBasin.solvePartOne(stream);

        checkAnswer(15L, answer);
    }

    @Test
    public void solvePartOne() throws Exception {
        Stream<String> stream = Files.lines(getInputFilePath(this));

        final Long answer = smokeBasin.solvePartOne(stream);

        checkAnswer(603L, answer);
    }

    @Test
    public void solvePartTwoSample() throws Exception {
        Stream<String> stream = getSampleInput(this);

        final Long answer = smokeBasin.solvePartTwo(stream);

        checkAnswer(1134L, answer);
    }

    @Test
    public void solvePartTwo() throws Exception {
        Stream<String> stream = Files.lines(getInputFilePath(this));

        final Long answer = smokeBasin.solvePartTwo(stream);

        checkAnswer(786780L, answer);
    }

    @Test
    public void solve() throws Exception {
        smokeBasin.solve();
    }
}
