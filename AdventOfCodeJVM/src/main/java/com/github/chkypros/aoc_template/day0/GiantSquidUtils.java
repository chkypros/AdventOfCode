package com.github.chkypros.aoc_template.day0;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GiantSquidUtils {
    private GiantSquidUtils() {
        // Utility class
    }

    static List<Integer> getDrawNumbers(List<String> input) {
        return Arrays.stream(input.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    static Set<BingoBoard> getBingoBoards(List<String> input) {
        final Set<BingoBoard> bingoBoards = new HashSet<>();

        for (int i = 0; i < input.size(); i+= 6) {
            int[][] board = new int[BingoBoard.SIZE][BingoBoard.SIZE];
            for (int j = 0; j < BingoBoard.SIZE; j++) {
                final int inputIndex = i + j;
                final int[] rowNumbers = Arrays.stream(input.get(inputIndex).split(" +"))
                        .filter(string -> !"".equals(string))
                        .mapToInt(Integer::valueOf)
                        .toArray();

                System.arraycopy(rowNumbers, 0, board[j], 0, BingoBoard.SIZE);
            }
            bingoBoards.add(new BingoBoard(board));
        }

        return bingoBoards;
    }
}
