package com.github.chkypros.aoc_template.day0;

public class BingoBoard {
    public static final int SIZE = 5;

    private final int[][] board;
    private final boolean[][] checked = new boolean[SIZE][SIZE];

    private boolean hasCompleted;

    public BingoBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean checkNumber(final int number) {
        if (hasCompleted) {
            return false;
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (number == board[i][j]) {
                    checked[i][j] = true;
                    if (isCompleteRow(i) || isCompleteColumn(j)) {
                        hasCompleted = true;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isCompleteRow(int i) {
        for (int j = 0; j < SIZE; j++) {
            if (!checked[i][j]) {
                return false;
            }
        }
        return true;
    }

    private boolean isCompleteColumn(int j) {
        for (int i = 0; i < SIZE; i++) {
            if (!checked[i][j]) {
                return false;
            }
        }
        return true;
    }

    public long getUnmarkedScore() {
        long sum = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!checked[i][j]) {
                    sum += board[i][j];
                }
            }
        }

        return sum;
    }
}
