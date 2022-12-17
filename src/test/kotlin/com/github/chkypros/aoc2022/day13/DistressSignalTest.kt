package com.github.chkypros.aoc2022.day13

import com.github.chkypros.aoc_template.AbstractSolutionTest
import com.github.chkypros.aoc_template.TestUtils.checkAnswer
import org.junit.Before
import org.junit.Test
import java.util.stream.Stream

class DistressSignalTest: AbstractSolutionTest() {
    @Before
    fun setUp() {
        solution = DistressSignal()
        expectedAnswerPartOneSample = 13
        expectedAnswerPartTwoSample = 140
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
    fun partOne_singleElementLists_equal() {
        val answer = solution.solvePartOne(Stream.of("[1]", "[1]"))
        checkAnswer(1, answer)
    }

    @Test
    fun partOne_singleElementLists_unordered() {
        val answer = solution.solvePartOne(Stream.of("[2]", "[1]"))
        checkAnswer(0, answer)
    }
}
