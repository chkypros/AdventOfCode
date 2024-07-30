package com.github.chkypros.aoc2022.day13

import com.github.chkypros.aoc_common.TestUtils.checkAnswer
import com.github.chkypros.aoc_template.AbstractSolutionTest
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
