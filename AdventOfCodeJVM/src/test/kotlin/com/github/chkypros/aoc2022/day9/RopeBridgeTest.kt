package com.github.chkypros.aoc2022.day9

import com.github.chkypros.aoc_template.AbstractSolutionTest
import com.github.chkypros.aoc_template.TestUtils.checkAnswer
import org.junit.Before

import org.junit.Test
import java.util.stream.Stream

/**
 * @author [Kypros Chrysanthou](mailto:kypros.chrysanthou@britebill.com)
 */
class RopeBridgeTest : AbstractSolutionTest() {
    @Before
    fun setUp() {
        solution = RopeBridge()
        expectedAnswerPartOneSample = 13
        expectedAnswerPartTwoSample = 1
    }

    @Test
    override fun solvePartOneSample() {
        super.solvePartOneSample()
    }

    @Test
    override fun solvePartTwoSample() {
        super.solvePartTwoSample()
    }

    @Test
    fun partOne_noTailMove() {
        val answer = solution.solvePartOne(Stream.of("R 1"))
        checkAnswer(1, answer)
    }

    @Test
    fun partOne_singleTailMove() {
        val answer = solution.solvePartOne(Stream.of("R 2"))
        checkAnswer(2, answer)
    }

    @Test
    fun partTwo_biggerExample() {
        val stream = Stream.of("R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20")
        val answer = solution.solvePartTwo(stream)
        checkAnswer(36, answer)
    }
}
