package com.github.chkypros.aoc2022.day5;

import com.github.chkypros.aoc_template.AbstractSolutionTest;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class SupplyStacksTest extends AbstractSolutionTest {

    @Before
    public void setUp() {
        solution = new SupplyStacks();
        expectedAnswerPartOneSample = "CMZ";
        expectedAnswerPartTwoSample = "MCD";
    }

    @Test
    @Override
    public void solvePartOneSample() throws Exception {
        super.solvePartOneSample();
    }

    @Test
    @Override
    public void solvePartTwoSample() throws Exception {
        super.solvePartTwoSample();
    }

    @Test
    public void partOne_twoColumns_noMove_singleItems() {
        String[] lines = {
                "[X] [Y]",
                " 1   2 ",
                ""
        };
        var answer = solution.solvePartOne(Stream.of(lines));
        checkAnswer("XY", answer);
    }

    @Test
    public void partOne_threeColumns_noMove_twoStack() {
        String[] lines = {
                "    [Y] [Z]",
                "[X] [A] [B]",
                " 1   2   3 ",
                ""
        };
        var answer = solution.solvePartOne(Stream.of(lines));
        checkAnswer("XYZ", answer);
    }

    @Test
    public void partOne_threeColumns_singleMove() {
        String[] lines = {
                "    [Y] [Z]",
                "[X] [A] [B]",
                " 1   2   3 ",
                "",
                "move 1 from 3 to 1"
        };
        var answer = solution.solvePartOne(Stream.of(lines));
        checkAnswer("ZYB", answer);
    }

    @Test
    public void partTwo_threeColumns_singleMove_twoCrates() {
        String[] lines = {
                "    [K] [C]",
                "    [Y] [Z]",
                "[X] [A] [B]",
                " 1   2   3 ",
                "",
                "move 2 from 3 to 1"
        };
        var answer = solution.solvePartTwo(Stream.of(lines));
        checkAnswer("CKB", answer);
    }
}
