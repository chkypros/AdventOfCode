package com.github.chkypros.aoc2021.day16;

import com.github.chkypros.aoc_template.AbstractSolutionTest;
import org.junit.Test;

import java.util.stream.Stream;

public class PacketDecoderTest extends AbstractSolutionTest {
    public PacketDecoderTest() {
        solution = new PacketDecoder();
        expectedAnswerPartOneSample = 31L;
        expectedAnswerPartOne = 852L;
    }

    @Test
    public void solvePartOne_singleLiteral() {
        solvePartOne(6L, Stream.of("D2FE28"));
    }

    @Test
    public void solvePartOne_singleOperator_lengthIdZero_twoLiterals() {
        solvePartOne(9L, Stream.of("38006F45291200"));
    }

    @Test
    public void solvePartOne_operatorInOperator() {
        solvePartOne(23L, Stream.of("C0015000016115A2E0802F182340"));
    }

    @Test
    public void solvePartOne_singleOperator_lengthIdOne_threeLiterals() {
        solvePartOne(14L, Stream.of("EE00D40C823060"));
    }

    @Test
    public void solvePartTwo_sumOperator_twoLiterals() {
        solvePartTwo(3L, Stream.of("C200B40A82"));
    }
}
