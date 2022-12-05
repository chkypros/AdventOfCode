package com.github.chkypros.aoc2022.day4;

import com.github.chkypros.aoc_template.AbstractSolutionTest;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;

public class CampCleanupTest extends AbstractSolutionTest {

    @Before
    public void setUp() {
        solution = new CampCleanup();
        expectedAnswerPartOneSample = 2L;
        expectedAnswerPartTwoSample = 4L;
    }

    @Test
    @Override
    public void solvePartOneSample() throws Exception {
        super.solvePartOneSample();
    }

    @Test
    @Override
    public void solvePartTwoSample() throws Exception {
        super.solvePartTwoSample();
    }

    @Test
    public void partOne_singlePair_noContain() {
        final var answer = solution.solvePartOne(Stream.of("1-2,2-3"));
        checkAnswer(0L, answer);
    }

    @Test
    public void partOne_singlePair_contained() {
        final var answer = solution.solvePartOne(Stream.of("1-3,2-3"));
        checkAnswer(1L, answer);
    }
}
