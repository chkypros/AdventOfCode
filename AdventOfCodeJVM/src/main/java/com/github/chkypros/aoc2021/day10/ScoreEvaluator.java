package com.github.chkypros.aoc2021.day10;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ScoreEvaluator {
    private static final Map<Character, Integer> ILLEGAL_BRACE_SCORE = new HashMap<>();
    private static final Map<Character, Integer> MISSING_BRACE_SCORE = new HashMap<>();

    static {
        ILLEGAL_BRACE_SCORE.put(')', 3);
        ILLEGAL_BRACE_SCORE.put(']', 57);
        ILLEGAL_BRACE_SCORE.put('}', 1197);
        ILLEGAL_BRACE_SCORE.put('>', 25137);

        MISSING_BRACE_SCORE.put(')', 1);
        MISSING_BRACE_SCORE.put(']', 2);
        MISSING_BRACE_SCORE.put('}', 3);
        MISSING_BRACE_SCORE.put('>', 4);
    }

    public static int getIllegalBraceScore(Character character) {
        return ILLEGAL_BRACE_SCORE.get(character);
    }

    public static long getMissingBracesScore(String missingBraces) {
        return IntStream.range(0, missingBraces.length())
                .mapToObj(missingBraces::charAt)
                .mapToLong(MISSING_BRACE_SCORE::get)
                .reduce((left, right) -> left * 5 + right)
                .getAsLong();
    }
}
