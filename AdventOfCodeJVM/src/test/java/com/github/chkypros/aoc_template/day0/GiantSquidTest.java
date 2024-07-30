package com.github.chkypros.aoc_template.day0;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.*;

public class GiantSquidTest extends AbstractSolutionTest {

    @Before
    public void setUp() throws Exception {
        this.solution = new GiantSquid();

        this.expectedAnswerPartOneSample = 4512L;
        this.expectedAnswerPartTwoSample = 1924L;
    }
}
