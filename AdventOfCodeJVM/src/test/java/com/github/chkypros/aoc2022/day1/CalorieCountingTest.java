package com.github.chkypros.aoc2022.day1;

import com.github.chkypros.aoc_template.AbstractSolutionTest;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;

public class CalorieCountingTest extends AbstractSolutionTest {

    @Before
    public void setUp() {
        solution = new CalorieCounting();
        expectedAnswerPartOneSample = 24000L;
        expectedAnswerPartTwoSample = 45000L;
    }

    @Test
    public void solveSingleItemInput() {
        String calories = "1000";
        final var answer = solution.solvePartOne(Stream.of(calories));
        checkAnswer(Long.parseLong(calories), answer);
    }

    @Test
    public void solveMultipleElfsWithSingleItemInput() {
        String[] calories = {"1000", "", "2000"};
        final var answer = solution.solvePartOne(Stream.of(calories));
        checkAnswer(Long.parseLong(calories[2]), answer);
    }
}
