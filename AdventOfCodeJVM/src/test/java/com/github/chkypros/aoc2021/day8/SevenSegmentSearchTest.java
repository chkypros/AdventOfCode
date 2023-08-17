package com.github.chkypros.aoc2021.day8;

import com.github.chkypros.aoc_common.Utils;
import org.junit.Test;

import java.nio.file.Files;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_common.TestUtils.checkAnswer;
import static com.github.chkypros.aoc_common.TestUtils.getSampleInput;

public class SevenSegmentSearchTest {
    private final SevenSegmentSearch sevenSegmentSearch = new SevenSegmentSearch();

    @Test
    public void solvePartOneSample() throws Exception {
        Stream<String> stream = getSampleInput(this);

        final Long answer = sevenSegmentSearch.solvePartOne(stream);

        checkAnswer(26L, answer);
    }

    @Test
    public void solvePartOne() throws Exception {
        final Stream<String> stream = Files.lines(Utils.getInputFilePath(this));

        final Long answer = sevenSegmentSearch.solvePartOne(stream);

        checkAnswer(476L, answer);
    }

    @Test
    public void solvePartTwoSample() throws Exception {
        Stream<String> stream = getSampleInput(this);

        final var answer = sevenSegmentSearch.solvePartTwo(stream);

        checkAnswer(61229L, answer);
    }

    @Test
    public void solvePartTwo() throws Exception {
        final Stream<String> stream = Files.lines(Utils.getInputFilePath(this));

        final var answer = sevenSegmentSearch.solvePartTwo(stream);

        checkAnswer(1011823L, answer);
    }

    @Test
    public void solve() throws Exception {
        sevenSegmentSearch.solve();
    }
}
