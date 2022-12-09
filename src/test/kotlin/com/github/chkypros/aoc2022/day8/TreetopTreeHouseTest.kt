package com.github.chkypros.aoc2022.day8

import com.github.chkypros.aoc_template.AbstractSolutionTest
import com.github.chkypros.aoc_template.TestUtils.checkAnswer
import org.junit.Before
import org.junit.Test
import java.util.stream.Stream

/**
 * @author [Kypros Chrysanthou](mailto:kypros.chrysanthou@britebill.com)
 */
class TreetopTreeHouseTest : AbstractSolutionTest() {
    @Before
    fun setUp() {
        solution = TreetopTreeHouse()
        expectedAnswerPartOneSample = 21
        expectedAnswerPartTwoSample = 8
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
    fun partOne_singleElement() {
        val answer = solution.solvePartOne(Stream.of("1"))
        checkAnswer(1, answer)
    }

    @Test
    fun partOne_singleRow() {
        val answer = solution.solvePartOne(Stream.of("123"))
        checkAnswer(3, answer)
    }

    @Test
    fun partOne_allVisible() {
        val answer = solution.solvePartOne(Stream.of("123", "456"))
        checkAnswer(6, answer)
    }

    @Test
    fun partOne_singleHidden() {
        val answer = solution.solvePartOne(Stream.of("123", "406", "789"))
        checkAnswer(8, answer)
    }
}
