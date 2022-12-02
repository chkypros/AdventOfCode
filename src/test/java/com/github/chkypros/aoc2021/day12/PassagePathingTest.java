package com.github.chkypros.aoc2021.day12;

import com.github.chkypros.aoc_template.AbstractSolutionTest;
import com.github.chkypros.aoc_template.Utils;
import org.junit.Test;

import java.nio.file.Files;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;

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

        final Long answer = solution.solvePartOne(stream);

        checkAnswer(10, answer);
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

        final Long answer = solution.solvePartTwo(stream);

        checkAnswer(36, answer);
    }

    @Test
    @Override
    public void solvePartTwoSample() throws Exception {
        super.solvePartTwoSample();
    }
}
