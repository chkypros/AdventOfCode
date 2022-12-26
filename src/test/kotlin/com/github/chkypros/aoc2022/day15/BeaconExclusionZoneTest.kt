package com.github.chkypros.aoc2022.day15

import com.github.chkypros.aoc_template.AbstractSolutionTest
import org.junit.Before

import org.junit.Test

class BeaconExclusionZoneTest: AbstractSolutionTest() {

    @Before
    fun setUp() {
        solution = BeaconExclusionZone()
        expectedAnswerPartOneSample = 26
        expectedAnswerPartTwoSample = 56_000_011
    }

    @Test
    override fun solvePartOneSample() {
        (solution as BeaconExclusionZone).rowOfInterest = 10
        super.solvePartOneSample()
    }

    @Test
    override fun solvePartTwoSample() {
        (solution as BeaconExclusionZone).searchRange = 20
        super.solvePartTwoSample()
    }
}
