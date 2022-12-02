package com.github.chkypros.aoc2021.day3;

import org.junit.Test;

import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;

public class BinaryDiagnosticTest {

    private final BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();

    @Test
    public void partOneSingleItem() {
        Stream<String> stream = Stream.of("0010");

        Long answer = binaryDiagnostic.solvePartOne(stream);

        checkAnswer(4, answer);
    }

    @Test
    public void partOneMultipleItems() {
        Stream<String> stream = getSampleInput();

        Long answer = binaryDiagnostic.solvePartOne(stream);

        checkAnswer(198, answer);
    }

    @Test
    public void partTwoMultipleItems() {
        Stream<String> stream = getSampleInput();

        Long answer = binaryDiagnostic.solvePartTwo(stream);

        checkAnswer(230, answer);
    }

    @Test
    public void solve() throws Exception {
        binaryDiagnostic.solve();
    }

    private Stream<String> getSampleInput() {
        return Stream.of(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010");
    }
}
