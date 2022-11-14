package com.gitlab.chkypros.aoc_template;

import java.nio.file.Files;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static com.gitlab.chkypros.aoc_template.Utils.getInputFilePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUtils {
    private TestUtils() {
        // Utility class
    }

    public static void solvePartOneSample(AbstractSolution solution, Long expectedAnswer) throws Exception {
        solvePartSample(solution, expectedAnswer, AbstractSolution::solvePartOne);
    }

    public static void solvePartTwoSample(AbstractSolution solution, Long expectedAnswer) throws Exception {
        solvePartSample(solution, expectedAnswer, AbstractSolution::solvePartTwo);
    }

    private static void solvePartSample(AbstractSolution solution, Long expectedAnswer, BiFunction<AbstractSolution, Stream<String>, Long> solutionMethod) throws Exception {
        Stream<String> stream = getSampleInput(solution);

        final Long answer = solutionMethod.apply(solution, stream);

        checkAnswer(expectedAnswer, answer);
    }

    public static void checkAnswer(long expectedAnswer, Long answer) {
        assertNotNull(answer);
        assertEquals(expectedAnswer, answer.longValue());
    }

    public static Stream<String> getSampleInput(final Object object) throws Exception {
        return Files.lines(getInputFilePath(object, "-sample"));
    }
}
