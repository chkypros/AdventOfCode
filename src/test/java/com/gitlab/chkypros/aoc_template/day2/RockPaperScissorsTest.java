package com.gitlab.chkypros.aoc_template.day2;

import com.gitlab.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static com.gitlab.chkypros.aoc_template.TestUtils.checkAnswer;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class RockPaperScissorsTest extends AbstractSolutionTest {

    @Before
    public void setUp() {
        solution = new RockPaperScissors();
        expectedAnswerPartOneSample = 15;
        expectedAnswerPartTwoSample = 12;
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
    public void singleRound_draw_scissors() {
        Long answer = solution.solvePartOne(Stream.of("C Z"));
        checkAnswer(6L, answer);
    }

    @Test
    public void singleRound_draw_rocks() {
        Long answer = solution.solvePartOne(Stream.of("A X"));
        checkAnswer(4L, answer);
    }

    @Test
    public void singleRound_win_paper() {
        Long answer = solution.solvePartOne(Stream.of("A Y"));
        checkAnswer(8L, answer);
    }

    @Test
    public void singleRound_draw_part_two() {
        Long answer = solution.solvePartTwo(Stream.of("A Y"));
        checkAnswer(4L, answer);
    }
}
