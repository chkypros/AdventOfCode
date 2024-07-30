package com.github.chkypros.aoc2021.day4;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;


public class GiantSquidTest extends AbstractSolutionTest {

    @Before
    public void setUp() throws Exception {
        this.solution = new GiantSquid();

        this.expectedAnswerPartOneSample = 4512L;
        this.expectedAnswerPartTwoSample = 1924L;
    }
}
