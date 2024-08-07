package com.github.chkypros.aoc_common;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.IntStream;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static Path getInputFilePath(Object object) throws URISyntaxException {
        return getInputFilePath(object, "");
    }

    public static Path getInputFilePath(Object object, String label) throws URISyntaxException {
        final String packageName = object.getClass().getPackage().getName();
        final int lastIndexOfPeriod = packageName.lastIndexOf(".");
        final int secondToLastIndexOfPeriod = packageName.lastIndexOf(".", lastIndexOfPeriod - 1);

        final String day = packageName.substring(lastIndexOfPeriod + 1);
        final String event = packageName.substring(secondToLastIndexOfPeriod + 1, lastIndexOfPeriod);

        final String resource = event + "/" + day + "/aoc-" + day + label + "-input.txt";
        URL inputFile = Objects.requireNonNull(ClassLoader.getSystemResource(resource), "Input file not found: " + resource);
        return Paths.get(inputFile.toURI());
    }

    public static String hexToBinary(String hexadecimal) {
        final String binaryWithoutLeadingZeroes = new BigInteger(hexadecimal, 16).toString(2);
        final String zeroPadding = IntStream.range(0, hexadecimal.length() * 4 - binaryWithoutLeadingZeroes.length())
                .mapToObj(i -> "0")
                .reduce("", (s, s2) -> s + s2);
        return zeroPadding + binaryWithoutLeadingZeroes;
    }
}
