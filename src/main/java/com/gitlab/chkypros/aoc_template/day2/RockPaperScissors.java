package com.gitlab.chkypros.aoc_template.day2;

import com.gitlab.chkypros.aoc_template.AbstractSolution;

import java.util.stream.Stream;

import static com.gitlab.chkypros.aoc_template.day2.RPSChoice.getChoiceForLetter;
import static com.gitlab.chkypros.aoc_template.day2.RPSOutcome.DRAW;
import static com.gitlab.chkypros.aoc_template.day2.RPSOutcome.LOSS;
import static com.gitlab.chkypros.aoc_template.day2.RPSOutcome.WIN;
import static com.gitlab.chkypros.aoc_template.day2.RPSOutcome.getOutcomeForLetter;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class RockPaperScissors extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return stream.mapToLong(this::evaluateRoundPartOne)
            .sum();
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        return stream.mapToLong(this::evaluateRoundPartTwo)
            .sum();
    }

    private long evaluateRoundPartOne(String round) {
        String[] choices = round.split(" ");
        RPSChoice opponentChoice = getChoiceForLetter(choices[0]);
        RPSChoice playerChoice = getChoiceForLetter(choices[1]);
        return getRoundScore(opponentChoice, playerChoice);
    }

    private long evaluateRoundPartTwo(String roundInfo) {
        String[] choices = roundInfo.split(" ");
        RPSChoice opponentChoice = getChoiceForLetter(choices[0]);
        RPSOutcome desiredOutcome = getOutcomeForLetter(choices[1]);

        RPSChoice playerChoice = switch (desiredOutcome) {
            case LOSS -> opponentChoice.winsOver;
            case DRAW -> opponentChoice;
            case WIN -> opponentChoice.losesTo;
        };

        return getRoundScore(opponentChoice, playerChoice);
    }

    private long getRoundScore(RPSChoice opponentChoice, RPSChoice playerChoice) {
        RPSOutcome roundOutcome = getRoundOutcome(playerChoice, opponentChoice);
        return roundOutcome.score + playerChoice.score;
    }

    private RPSOutcome getRoundOutcome(RPSChoice playerChoice, RPSChoice opponentChoice) {
        if (playerChoice.winsOver == opponentChoice) return WIN;
        else if (playerChoice.losesTo == opponentChoice) return LOSS;
        else return DRAW;
    }
}
