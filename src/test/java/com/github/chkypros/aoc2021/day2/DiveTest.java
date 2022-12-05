package com.github.chkypros.aoc2021.day2;

import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc2021.day2.DiveAction.*;
import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;

public class DiveTest {

    @Test
    public void partOneForward() {
        final Dive dive = new Dive(0, 1);
        Stream<String> stream = Stream.of(FORWARD + " 5");

        Long answer = dive.solvePartOne(stream);

        checkAnswer(5L, answer);
    }

    @Test
    public void partOneDown() {
        final Dive dive = new Dive(1, 0);
        Stream<String> stream = Stream.of(DOWN + " 5");

        Long answer = dive.solvePartOne(stream);

        checkAnswer(5L, answer);
    }

    @Test
    public void partOneUp() {
        final Dive dive = new Dive(1, 10);
        Stream<String> stream = Stream.of(UP + " 5");

        Long answer = dive.solvePartOne(stream);

        checkAnswer(5L, answer);
    }

    @Test
    public void partOneForwardDown() {
        final Dive dive = new Dive(0, 0);
        Stream<String> stream = Stream.of(FORWARD + " 4", DOWN + " 3");

        Long answer = dive.solvePartOne(stream);

        checkAnswer(12L, answer);
    }

    @Test
    public void partTwoForward() {
        final Dive dive = new Dive(0, 1);
        Stream<String> stream = Stream.of(FORWARD + " 5");

        var answer = dive.solvePartTwo(stream);

        checkAnswer(5L, answer);
    }

    @Test
    public void partTwoDownForward() {
        final Dive dive = new Dive(0, 0);
        Stream<String> stream = Stream.of(DOWN + " 3", FORWARD + " 4");

        var answer = dive.solvePartTwo(stream);

        checkAnswer(48L, answer);
    }

    @Test
    public void partTwoForwardDown() {
        final Dive dive = new Dive(0, 0);
        Stream<String> stream = Stream.of(DOWN + " 3", FORWARD + " 4");

        var answer = dive.solvePartTwo(stream);

        checkAnswer(48L, answer);
    }

    @Test
    public void solve() throws Exception {
        final Dive dive = new Dive(0, 0);
        dive.solve();
    }
}
