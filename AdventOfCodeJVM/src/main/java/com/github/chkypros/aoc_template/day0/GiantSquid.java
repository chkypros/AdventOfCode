package com.github.chkypros.aoc_template.day0;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GiantSquid extends AbstractSolution {
    @Override
    public Long solvePartOne(Stream<String> stream) {
        final List<String> input = stream.collect(Collectors.toList());
        final List<Integer> drawNumbers = GiantSquidUtils.getDrawNumbers(input);

        final Set<BingoBoard> bingoBoards = GiantSquidUtils.getBingoBoards(input.subList(2, input.size()));

        for (Integer drawNumber : drawNumbers) {
            for (BingoBoard bingoBoard : bingoBoards) {
                if (bingoBoard.checkNumber(drawNumber)) {
                    final long unmarkedScore = bingoBoard.getUnmarkedScore();
                    return unmarkedScore * drawNumber;
                }
            }
        }
        return null;
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final List<String> input = stream.collect(Collectors.toList());
        final List<Integer> drawNumbers = GiantSquidUtils.getDrawNumbers(input);

        final Set<BingoBoard> bingoBoards = GiantSquidUtils.getBingoBoards(input.subList(2, input.size()));

        int boardsToWin = bingoBoards.size();
        for (Integer drawNumber : drawNumbers) {
            for (BingoBoard bingoBoard : bingoBoards) {
                if (bingoBoard.checkNumber(drawNumber)) {
                    if (--boardsToWin == 0) {
                        final long unmarkedScore = bingoBoard.getUnmarkedScore();
                        return unmarkedScore * drawNumber;
                    }
                }
            }
        }
        return null;
    }

}
