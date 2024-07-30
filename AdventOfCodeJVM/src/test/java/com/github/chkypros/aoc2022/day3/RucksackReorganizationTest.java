package com.github.chkypros.aoc2022.day3;

import com.github.chkypros.aoc_template.AbstractSolutionTest;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;

public class RucksackReorganizationTest extends AbstractSolutionTest {

    @Before
    public void setUp() {
        solution = new RucksackReorganization();
        expectedAnswerPartOneSample = 157L;
        expectedAnswerPartTwoSample = 70L;
    }

    @Test
    public void partOne_findCommonType_twoItems() {
        final var answer = solution.solvePartOne(Stream.of("pp"));
        checkAnswer(16L, answer);
    }

    @Test
    public void partOne_findCommonType_twoItems_capitals() {
        final var answer = solution.solvePartOne(Stream.of("LL"));
        checkAnswer(38L, answer);
    }

    @Test
    public void partTwo_findCommonType_singleGroup() {
        final var answer = solution.solvePartTwo(Stream.of("abc", "bcf", "cop"));
        checkAnswer(3L, answer);
    }
}
