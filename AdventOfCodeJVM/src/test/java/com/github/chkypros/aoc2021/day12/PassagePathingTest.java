package com.github.chkypros.aoc2021.day12;

import com.github.chkypros.aoc_common.TestUtils;
import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Test;

public class PassagePathingTest extends AbstractSolutionTest {
    public PassagePathingTest() {
        solution = new PassagePathing();
        expectedAnswerPartOneSample = 226L;
        expectedAnswerPartTwoSample = 3509L;
        expectedAnswerPartOne = 4775L;
    }

    @Test
    public void solvePartOneFirstExample() throws Exception {
        solvePartOne(10L, TestUtils.getLabeledInput(this, "-first-example"));
    }

    @Test
    public void solvePartTwoFirstExample() throws Exception {
        solvePartTwo(36L, TestUtils.getLabeledInput(this, "-first-example"));
    }
}
