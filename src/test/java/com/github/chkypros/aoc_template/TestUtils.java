package com.github.chkypros.aoc_template;

import java.nio.file.Files;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static com.github.chkypros.aoc_template.Utils.getInputFilePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUtils {
    private TestUtils() {
        // Utility class
    }

    public static void solvePartOneSample(AbstractSolution solution, Object expectedAnswer) throws Exception {
        solvePartSample(solution, expectedAnswer, AbstractSolution::solvePartOne);
    }

    public static void solvePartTwoSample(AbstractSolution solution, Object expectedAnswer) throws Exception {
        solvePartSample(solution, expectedAnswer, AbstractSolution::solvePartTwo);
    }

    private static void solvePartSample(AbstractSolution solution, Object expectedAnswer, BiFunction<AbstractSolution, Stream<String>, Object> solutionMethod) throws Exception {
        Stream<String> stream = getSampleInput(solution);

        final Object answer = solutionMethod.apply(solution, stream);

        checkAnswer(expectedAnswer, answer);
    }

    public static void checkAnswer(Object expectedAnswer, Object answer) {
        assertNotNull(answer);
        assertEquals(String.valueOf(expectedAnswer), String.valueOf(answer));
    }

    public static Stream<String> getSampleInput(final Object object) throws Exception {
        return Files.lines(getInputFilePath(object, "-sample"));
    }
}
