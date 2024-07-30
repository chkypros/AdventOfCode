package com.github.chkypros.aoc2021.day7;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

public class TheTreacheryOfWhalesTest extends AbstractSolutionTest {
    private static final String SAMPLE_INPUT = "16,1,2,0,4,2,7,1,2,14";

    @Before
    public void setUp() throws Exception {
        this.solution = new TheTreacheryOfWhales();

        this.expectedAnswerPartOne = 349769L;
        this.expectedAnswerPartTwo = 99540554L;
    }

    @Test
    public void solvePartOneSample() {
        solvePartOne(37L, getSampleInput());
    }

    @Test
    public void solvePartTwoSample() {
        solvePartTwo(168L, getSampleInput());
    }

    private Stream<String> getSampleInput() {
        return Stream.of(SAMPLE_INPUT);
    }
}
