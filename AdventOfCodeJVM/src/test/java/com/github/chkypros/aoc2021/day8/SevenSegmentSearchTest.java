package com.github.chkypros.aoc2021.day8;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;

public class SevenSegmentSearchTest extends AbstractSolutionTest {

    @Before
    public void setUp() throws Exception {
        this.solution = new SevenSegmentSearch();

        this.expectedAnswerPartOneSample = 26L;
        this.expectedAnswerPartTwoSample = 61229L;
        this.expectedAnswerPartOne = 476L;
        this.expectedAnswerPartTwo = 1011823L;
    }
}
