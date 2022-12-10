package com.github.chkypros.aoc2022.day10

import com.github.chkypros.aoc_template.AbstractSolutionTest
import com.github.chkypros.aoc_template.TestUtils.checkAnswer
import org.junit.Before

import org.junit.Test
import java.util.stream.Stream

class CathodeRayTubeTest:AbstractSolutionTest() {
    @Before
    fun setUp() {
        solution = CathodeRayTube()
        expectedAnswerPartOneSample = 13140
    }

    @Test
    override fun solvePartOneSample() {
        super.solvePartOneSample()
    }

    @Test
    fun partOne_onlyNoop() {
        val answer = solution.solvePartOne(Stream.of("noop", "noop"))
        checkAnswer(0, answer)
    }

    @Test
    fun partOne_singleAdd() {
        val answer = solution.solvePartOne(Stream.of("noop", "addx 3"))
        checkAnswer(0, answer)
    }
}
