package com.github.chkypros.aoc_template;

import org.junit.Test;

import java.nio.file.Files;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;
import static com.github.chkypros.aoc_template.TestUtils.getSampleInput;
import static com.github.chkypros.aoc_template.Utils.getInputFilePath;

public abstract class AbstractSolutionTest {
    protected AbstractSolution solution;
    protected long expectedAnswerPartOneSample;
    protected long expectedAnswerPartTwoSample;
    protected long expectedAnswerPartOne;
    protected long expectedAnswerPartTwo;

    public void solvePartOneSample() throws Exception {
        solvePartOne(expectedAnswerPartOneSample, getSampleInput(solution));
    }

    public void solvePartTwoSample() throws Exception {
        solvePartTwo(expectedAnswerPartTwoSample, getSampleInput(solution));
    }

    public void solvePartOne() throws Exception {
        solvePartOne(expectedAnswerPartOne, Files.lines(getInputFilePath(solution)));
    }

    public void solvePartTwo() throws Exception {
        solvePartTwo(expectedAnswerPartTwo, Files.lines(getInputFilePath(solution)));
    }

    public void solvePartOne(long expectedAnswer, Stream<String> stream) {
        solvePart(expectedAnswer, stream, AbstractSolution::solvePartOne);
    }

    public void solvePartTwo(long expectedAnswer, Stream<String> stream) {
        solvePart(expectedAnswer, stream, AbstractSolution::solvePartTwo);
    }

    private void solvePart(Long expectedAnswer, Stream<String> stream, BiFunction<AbstractSolution, Stream<String>, Long> solvePartTwo) {
        final Long answer = solvePartTwo.apply(solution, stream);
        checkAnswer(expectedAnswer, answer);
    }

    @Test
    public void solve() throws Exception {
        solution.solve();
    }
}
