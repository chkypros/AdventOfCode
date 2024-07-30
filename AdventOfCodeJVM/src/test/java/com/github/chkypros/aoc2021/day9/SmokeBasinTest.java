package com.github.chkypros.aoc2021.day9;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;

public class SmokeBasinTest extends AbstractSolutionTest {

    @Before
    public void setUp() throws Exception {
        this.solution = new SmokeBasin();

        this.expectedAnswerPartOneSample = 15L;
        this.expectedAnswerPartTwoSample = 1134L;
        this.expectedAnswerPartOne = 603L;
        this.expectedAnswerPartTwo = 786780L;
    }
}
