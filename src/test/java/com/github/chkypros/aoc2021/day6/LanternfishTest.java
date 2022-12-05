package com.github.chkypros.aoc2021.day6;

import com.github.chkypros.aoc_template.Utils;
import org.junit.Test;

import java.nio.file.Files;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;

public class LanternfishTest {

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
        Stream<String> stream = Files.lines(Utils.getInputFilePath(this));
        final Long answer = new Lanternfish(80).solvePartOne(stream);
        System.out.println("answer = " + answer);
    }

    @Test
    public void solvePartTwo() throws Exception {
        Stream<String> stream = Files.lines(Utils.getInputFilePath(this));
        final var answer = new Lanternfish(256).solvePartTwo(stream);
        System.out.println("answer = " + answer);
    }
}
