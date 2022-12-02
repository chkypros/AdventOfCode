package com.github.chkypros.aoc2021.day7;

import com.github.chkypros.aoc_template.Utils;
import org.junit.Test;

import java.nio.file.Files;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.TestUtils.checkAnswer;

public class TheTreacheryOfWhalesTest {
    private static final String SAMPLE_INPUT = "16,1,2,0,4,2,7,1,2,14";


    @Test
    public void partOneSample() {
        final TheTreacheryOfWhales theTreacheryOfWhales = new TheTreacheryOfWhales();
        final Stream<String> stream = getSampleInput();

        final Long answer = theTreacheryOfWhales.solvePartOne(stream);

        checkAnswer(37L, answer);
    }

    @Test
    public void partOneInput() throws Exception {
        final TheTreacheryOfWhales theTreacheryOfWhales = new TheTreacheryOfWhales();
        final Stream<String> stream = Files.lines(Utils.getInputFilePath(this));

        final Long answer = theTreacheryOfWhales.solvePartOne(stream);

        checkAnswer(349769L, answer);
    }

    @Test
    public void partTwoSample() {
        final TheTreacheryOfWhales theTreacheryOfWhales = new TheTreacheryOfWhales();
        final Stream<String> stream = getSampleInput();

        final Long answer = theTreacheryOfWhales.solvePartTwo(stream);

        checkAnswer(168L, answer);
    }

    @Test
    public void partTwoInput() throws Exception {
        final TheTreacheryOfWhales theTreacheryOfWhales = new TheTreacheryOfWhales();
        final Stream<String> stream = Files.lines(Utils.getInputFilePath(this));

        final Long answer = theTreacheryOfWhales.solvePartTwo(stream);

        checkAnswer(99540554L, answer);
    }

    @Test
    public void solve() throws Exception {
        final TheTreacheryOfWhales theTreacheryOfWhales = new TheTreacheryOfWhales();
        theTreacheryOfWhales.solve();
    }

    private Stream<String> getSampleInput() {
        return Stream.of(SAMPLE_INPUT);
    }
}
