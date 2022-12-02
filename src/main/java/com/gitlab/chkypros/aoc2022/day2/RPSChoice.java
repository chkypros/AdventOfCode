package com.gitlab.chkypros.aoc2022.day2;

import java.util.Arrays;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public enum RPSChoice {
    ROCK("X", "A", 1L),
    PAPER("Y", "B", 2L),
    SCISSORS("Z", "C", 3L);

    final String playerLetter;
    final String opponentLetter;
    final long score;
    RPSChoice winsOver;
    RPSChoice losesTo;

    static {
        ROCK.winsOver = SCISSORS;
        PAPER.winsOver = ROCK;
        SCISSORS.winsOver = PAPER;

        ROCK.losesTo = PAPER;
        PAPER.losesTo = SCISSORS;
        SCISSORS.losesTo = ROCK;
    }

    RPSChoice(String playerLetter, String opponentLetter, long score) {
        this.playerLetter = playerLetter;
        this.opponentLetter = opponentLetter;
        this.score = score;
    }

    public static RPSChoice getChoiceForLetter(String letter) {
        return Arrays.stream(RPSChoice.values())
            .filter(c -> c.playerLetter.equals(letter) || c.opponentLetter.equals(letter))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(letter + " is not a valid choice letter"));
    }
}
