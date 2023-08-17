package com.github.chkypros.aoc2021.day12;

import com.github.chkypros.aoc_template.AbstractSolutionTest;
import com.github.chkypros.aoc_common.Utils;
import org.junit.Test;

import java.nio.file.Files;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;

public class PassagePathingTest extends AbstractSolutionTest {
    public PassagePathingTest() {
        solution = new PassagePathing();
        expectedAnswerPartOneSample = 226L;
        expectedAnswerPartTwoSample = 3509L;
        expectedAnswerPartOne = 4775L;
    }

    @Test
    public void solvePartOneFirstExample() throws Exception {
        final Stream<String> stream = Files.lines(Utils.getInputFilePath(this, "-first-example"));

        final var answer = solution.solvePartOne(stream);

        checkAnswer(10L, answer);
    }

    @Test
    @Override
    public void solvePartOneSample() throws Exception {
        super.solvePartOneSample();
    }

    @Test
    @Override
    public void solvePartOne() throws Exception {
        super.solvePartOne();
    }

    @Test
    public void solvePartTwoFirstExample() throws Exception {
        final Stream<String> stream = Files.lines(Utils.getInputFilePath(this, "-first-example"));

        final var answer = solution.solvePartTwo(stream);

        checkAnswer(36L, answer);
    }

    @Test
    @Override
    public void solvePartTwoSample() throws Exception {
        super.solvePartTwoSample();
    }
}
