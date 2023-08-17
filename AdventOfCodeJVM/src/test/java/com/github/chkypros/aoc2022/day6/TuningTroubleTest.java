package com.github.chkypros.aoc2022.day6;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class TuningTroubleTest extends AbstractSolutionTest {

    @Before
    public void setUp() throws Exception {
        solution = new TuningTrouble();
        expectedAnswerPartOneSample = 11;
    }

    @Test
    @Override
    public void solvePartOneSample() throws Exception {
        super.solvePartOneSample();
    }

    @Test
    public void partOne_allUnique() {
        var answer = solution.solvePartOne(Stream.of("abcd"));
        checkAnswer(4, answer);
    }
}
