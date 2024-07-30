package com.github.chkypros.aoc2021.day6;

import com.github.chkypros.aoc_template.AbstractSolutionTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;

public class LanternfishTest extends AbstractSolutionTest {

    @Before
    public void setUp() throws Exception {
        this.expectedAnswerPartOne = 349549L;
        this.expectedAnswerPartTwo = 1589590444365L;
    }

    @Test
    public void solvePartOneSample_18days() {
        final Lanternfish lanternfish = new Lanternfish(18);
        final Stream<String> stream = Stream.of("3,4,3,1,2");

        final Long answer = lanternfish.solvePartOne(stream);

        checkAnswer(26L, answer);
    }

    @Test
    public void solvePartOneSample_80days() {
        final Lanternfish lanternfish = new Lanternfish(80);
        final Stream<String> stream = Stream.of("3,4,3,1,2");

        final Long answer = lanternfish.solvePartOne(stream);

        checkAnswer(5934L, answer);
    }

    @Test
    public void solvePartTwoSingle_18days() {
        final Lanternfish lanternfish = new Lanternfish(18);
        final Stream<String> stream = Stream.of("1");

        final var answer = lanternfish.solvePartTwo(stream);

        checkAnswer(7L, answer);
    }

    @Test
    public void solvePartTwoSample_256days() {
        final Lanternfish lanternfish = new Lanternfish(256);
        final Stream<String> stream = Stream.of("3,4,3,1,2");

        final var answer = lanternfish.solvePartTwo(stream);

        checkAnswer(26984457539L, answer);
    }

    @Test
    public void solvePartOne() throws Exception {
        this.solution = new Lanternfish(80);
        super.solvePartOne();
    }

    @Test
    public void solvePartTwo() throws Exception {
        this.solution = new Lanternfish(256);
        super.solvePartTwo();
    }

    @Test
    @Ignore("Each part needs separate initialization")
    @Override
    public void solve() throws Exception {
        throw new UnsupportedOperationException();
    }
}
