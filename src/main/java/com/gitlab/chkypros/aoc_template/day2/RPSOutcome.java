package com.gitlab.chkypros.aoc_template.day2;

import java.util.Arrays;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public enum RPSOutcome {
    LOSS("X", 0L),
    DRAW("Y", 3L),
    WIN("Z", 6L);

    private final String letter;
    final long score;

    RPSOutcome(String letter, long score) {
        this.letter = letter;
        this.score = score;
    }

    public static RPSOutcome getOutcomeForLetter(String letter) {
        return Arrays.stream(RPSOutcome.values())
            .filter(o -> o.letter.equals(letter))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(letter + " is not a valid outcome letter"));
    }
}
