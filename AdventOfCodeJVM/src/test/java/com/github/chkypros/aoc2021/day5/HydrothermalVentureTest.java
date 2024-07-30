package com.github.chkypros.aoc2021.day5;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;

public class HydrothermalVentureTest extends AbstractSolutionTest {

    @Before
    public void setUp() throws Exception {
        this.solution = new HydrothermalVenture();

        this.expectedAnswerPartOneSample = 5L;
        this.expectedAnswerPartTwoSample = 12L;
    }
}
