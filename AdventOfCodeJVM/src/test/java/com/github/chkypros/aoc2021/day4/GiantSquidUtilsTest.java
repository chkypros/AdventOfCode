package com.github.chkypros.aoc2021.day4;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class GiantSquidUtilsTest {

    @Test
    public void testGetBingoBoards_singleBoard() {
        final List<String> input = Arrays.asList(
                "22 13 17 11  0",
                "8  2 23  4 24",
                "21  9 14 16  7",
                "6 10  3 18  5",
                "1 12 20 15 19"
        );

        final Set<BingoBoard> bingoBoards = GiantSquidUtils.getBingoBoards(input);

        assertNotNull(bingoBoards);
        assertEquals(1, bingoBoards.size());
        final BingoBoard bingoBoard = bingoBoards.stream().findAny().get();
        assertEquals(BingoBoard.SIZE, bingoBoard.getBoard().length);
        assertEquals(4, bingoBoard.getBoard()[1][3]);
    }

    @Test
    public void testGetBingoBoards_twoBoards() {
        final List<String> input = Arrays.asList(
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                " 6 10  3 18  5",
                " 1 12 20 15 19",
                "",
                " 3 15  0  2 22",
                " 9 18 13 17  5",
                "19  8  7 25 23",
                "20 11 10 24  4",
                "14 21 16 12  6"
        );

        final Set<BingoBoard> bingoBoards = GiantSquidUtils.getBingoBoards(input);

        assertNotNull(bingoBoards);
        assertEquals(2, bingoBoards.size());
        final BingoBoard bingoBoard = bingoBoards.stream().skip(1).findAny().get();
        assertTrue(17 == bingoBoard.getBoard()[1][3] || 4 == bingoBoard.getBoard()[1][3]);
    }
}
