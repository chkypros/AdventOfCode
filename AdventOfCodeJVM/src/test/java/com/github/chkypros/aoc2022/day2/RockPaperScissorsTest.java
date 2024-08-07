package com.github.chkypros.aoc2022.day2;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class RockPaperScissorsTest extends AbstractSolutionTest {

    @Before
    public void setUp() {
        solution = new RockPaperScissors();
        expectedAnswerPartOneSample = 15L;
        expectedAnswerPartTwoSample = 12L;
    }

    @Test
    public void singleRound_draw_scissors() {
        var answer = solution.solvePartOne(Stream.of("C Z"));
        checkAnswer(6L, answer);
    }

    @Test
    public void singleRound_draw_rocks() {
        var answer = solution.solvePartOne(Stream.of("A X"));
        checkAnswer(4L, answer);
    }

    @Test
    public void singleRound_win_paper() {
        var answer = solution.solvePartOne(Stream.of("A Y"));
        checkAnswer(8L, answer);
    }

    @Test
    public void singleRound_draw_part_two() {
        var answer = solution.solvePartTwo(Stream.of("A Y"));
        checkAnswer(4L, answer);
    }
}
