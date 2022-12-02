package com.github.chkypros.aoc2022.day2;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.stream.Stream;

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
        RPSChoice opponentChoice = RPSChoice.getChoiceForLetter(choices[0]);
        RPSChoice playerChoice = RPSChoice.getChoiceForLetter(choices[1]);
        return getRoundScore(opponentChoice, playerChoice);
    }

    private long evaluateRoundPartTwo(String roundInfo) {
        String[] choices = roundInfo.split(" ");
        RPSChoice opponentChoice = RPSChoice.getChoiceForLetter(choices[0]);
        RPSOutcome desiredOutcome = RPSOutcome.getOutcomeForLetter(choices[1]);

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
        if (playerChoice.winsOver == opponentChoice) return RPSOutcome.WIN;
        else if (playerChoice.losesTo == opponentChoice) return RPSOutcome.LOSS;
        else return RPSOutcome.DRAW;
    }
}
