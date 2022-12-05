package com.github.chkypros.aoc_template;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractSolution {
    public void solve() throws Exception {
        final Path inputFile = Utils.getInputFilePath(this);

        try (Stream<String> stream = Files.lines(inputFile)) {
            solvePart(stream, this::solvePartOne, "part one");
        }

        try (Stream<String> stream = Files.lines(inputFile)) {
            solvePart(stream, this::solvePartTwo, "part two");
        }
    }

    public Object solvePartOne(Stream<String> stream) {
        return null;
    }

    public Object solvePartTwo(Stream<String> stream) {
        return null;
    }

    private void solvePart(final Stream<String> stream, final Function<Stream<String>, Object> function, final String label) {
        Optional.ofNullable(function.apply(stream))
                .ifPresent(answer -> System.out.println(label + " answer = " + answer));
    }
}
