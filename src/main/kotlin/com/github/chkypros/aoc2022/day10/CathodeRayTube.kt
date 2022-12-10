package com.github.chkypros.aoc2022.day10

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream

class CathodeRayTube: AbstractSolution() {
    var register = 1
    var cycle = 1
    var signalStrength = 0
    lateinit var cyclesOfInterest: List<Int>

    override fun solvePartOne(stream: Stream<String>): Int {
        cyclesOfInterest = IntRange(0, 5).map { 20 + it * 40 }
        signalStrength = 0

        val instructions = stream.toList()
        instructions.forEach {
            updateSignalStrengthForCycle(cycle)

            if (it.startsWith("noop")) {
                cycle++
            } else {
                updateSignalStrengthForCycle(cycle + 1)

                val value = it.substring(5).toInt()
                register += value
                cycle += 2
            }
        }

        return signalStrength
    }

    private fun updateSignalStrengthForCycle(cycle: Int) {
        if (cyclesOfInterest.contains(cycle)) {
            signalStrength += cycle * register
        }
    }
}