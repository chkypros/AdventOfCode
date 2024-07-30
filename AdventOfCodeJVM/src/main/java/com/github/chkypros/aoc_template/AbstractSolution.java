package com.github.chkypros.aoc_template;

import com.github.chkypros.aoc_common.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractSolution {

    public void solve() throws Exception {
        final Path inputFile = Utils.getInputFilePath(this);

        solvePart(inputFile, this::solvePartOne, "part one");
        solvePart(inputFile, this::solvePartTwo, "part two");
    }

    public Object solvePartOne(Stream<String> stream) {
        return null;
    }

    public Object solvePartTwo(Stream<String> stream) {
        return null;
    }

    private void solvePart(final Path inputFilePath, final Function<Stream<String>, Object> function, final String label) throws IOException {
        try (Stream<String> stream = Files.lines(inputFilePath)) {
            Object answer = function.apply(stream);
            if (answer != null) System.out.println(label + " answer = " + answer);
        }
    }
}
