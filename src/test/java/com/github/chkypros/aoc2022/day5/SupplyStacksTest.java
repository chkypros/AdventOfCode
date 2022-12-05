package com.github.chkypros.aoc2022.day5;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class SupplyStacksTest extends AbstractSolutionTest {

    @Before
    public void setUp() {
        solution = new SupplyStacks();
        expectedAnswerPartOneSample = "CMZ";
    }

    @Test
    @Override
    public void solvePartOneSample() throws Exception {
        super.solvePartOneSample();
    }

    @Test
    public void partOne_two_columns_no_move() {
        String[] lines = """
            [A] [B]
             1   2
             
            """.split("\n");
        var answer = solution.solvePartOne(Stream.of(lines));
        checkAnswer("AB", answer);
    }

    @Test
    public void partOne_twoColumns_noMove_singleItems() {
        String[] lines = """
            [X] [Y]
             1   2
             
            """.split("\n");
        var answer = solution.solvePartOne(Stream.of(lines));
        checkAnswer("XY", answer);
    }
}
