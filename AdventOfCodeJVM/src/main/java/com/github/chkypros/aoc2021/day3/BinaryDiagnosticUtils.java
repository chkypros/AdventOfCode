package com.github.chkypros.aoc2021.day3;

public class BinaryDiagnosticUtils {
    private BinaryDiagnosticUtils() {
        // Helper class
    }

    static int[] toIntArray(char[] chars) {
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = '0' == chars[i] ? 0 : 1;
        }
        return ints;
    }

    static int[] sumArrays(int[] first, int[] second) {
        int[] sumArray = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            sumArray[i] = first[i] + second[i];
        }
        return sumArray;
    }

    static int toRate(final int[] array) {
        StringBuilder binary = new StringBuilder();
        for (int digit : array) {
            binary.append(digit);
        }
        return Integer.parseInt(binary.toString(), 2);
    }
}
