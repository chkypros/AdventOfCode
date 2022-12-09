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
    }

    @Test
    override fun solvePartOneSample() {
        super.solvePartOneSample()
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
}
