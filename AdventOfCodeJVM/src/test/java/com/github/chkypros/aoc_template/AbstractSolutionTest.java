package com.github.chkypros.aoc_template;

import org.junit.Test;

import java.nio.file.Files;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;
import static com.github.chkypros.aoc_common.TestUtils.getSampleInput;
import static com.github.chkypros.aoc_common.Utils.getInputFilePath;
import static org.junit.Assume.assumeNotNull;

public abstract class AbstractSolutionTest {
    protected AbstractSolution solution;
    protected Object expectedAnswerPartOneSample;
    protected Object expectedAnswerPartTwoSample;
    protected Object expectedAnswerPartOne;
    protected Object expectedAnswerPartTwo;

    @Test
    public void solvePartOneSample() throws Exception {
        assumeNotNull(expectedAnswerPartOneSample);
        solvePartOne(expectedAnswerPartOneSample, getSampleInput(solution));
    }

    @Test
    public void solvePartTwoSample() throws Exception {
        assumeNotNull(expectedAnswerPartTwoSample);
        solvePartTwo(expectedAnswerPartTwoSample, getSampleInput(solution));
    }

    @Test
    public void solvePartOne() throws Exception {
        assumeNotNull(expectedAnswerPartOne);
        solvePartOne(expectedAnswerPartOne, Files.lines(getInputFilePath(solution)));
    }

    @Test
    public void solvePartTwo() throws Exception {
        assumeNotNull(expectedAnswerPartTwo);
        solvePartTwo(expectedAnswerPartTwo, Files.lines(getInputFilePath(solution)));
    }

    public void solvePartOne(Object expectedAnswer, Stream<String> stream) {
        solvePart(expectedAnswer, stream, AbstractSolution::solvePartOne);
    }

    public void solvePartTwo(Object expectedAnswer, Stream<String> stream) {
        solvePart(expectedAnswer, stream, AbstractSolution::solvePartTwo);
    }

    private void solvePart(Object expectedAnswer, Stream<String> stream, BiFunction<AbstractSolution, Stream<String>, Object> solvePartTwo) {
        final Object answer = solvePartTwo.apply(solution, stream);
        checkAnswer(expectedAnswer, answer);
    }

    @Test(timeout = 180_000)
    public void solve() throws Exception {
        solution.solve();
    }
}
