package com.github.chkypros.aoc2022.day14

import com.github.chkypros.aoc_template.AbstractSolutionTest
import org.junit.Before

class RegolithReservoirTest: AbstractSolutionTest() {

    @Before
    fun setUp() {
        solution = RegolithReservoir()
        expectedAnswerPartOneSample = 24
        expectedAnswerPartTwoSample = 93
    }
}
