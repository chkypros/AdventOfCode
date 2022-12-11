package com.github.chkypros.aoc2022.day10

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream

private const val CRT_NUM_ROWS = 6
private const val CRT_NUM_COLUMNS = 40

class CathodeRayTube : AbstractSolution() {
    private val cyclesOfInterest = IntRange(0, 5).map { 20 + it * 40 }

    var register = 1
    var cycle = 1

    override fun solvePartOne(stream: Stream<String>): Int {
        var signalStrength = 0
        val instructions = stream.toList()

        register = 1
        cycle = 1

        instructions.forEach {
            signalStrength = getUpdatedSignalStrengthForCycle(cycle, signalStrength)

            if (it.startsWith("noop")) {
                cycle++
            } else {
                signalStrength = getUpdatedSignalStrengthForCycle(cycle + 1, signalStrength)

                val value = it.substring(5).toInt()
                register += value
                cycle += 2
            }
        }

        return signalStrength
    }

    private fun getUpdatedSignalStrengthForCycle(cycle: Int, signalStrength: Int): Int {
        return if (cyclesOfInterest.contains(cycle))
            signalStrength + cycle * register
        else
            signalStrength
    }

    override fun solvePartTwo(stream: Stream<String>): Any {
        val instructions = stream.toList()
        val crt = Array(CRT_NUM_ROWS) { CharArray(CRT_NUM_COLUMNS) { '.' } }

        register = 1
        cycle = 1

        instructions.forEach {
            signalStrength = getUpdatedSignalStrengthForCycle(cycle, signalStrength)

            if (it.startsWith("noop")) {
                cycle++
            } else {
                signalStrength = getUpdatedSignalStrengthForCycle(cycle + 1, signalStrength)

                val value = it.substring(5).toInt()
                register += value
                cycle += 2
            }
        }

        printCRT(crt)
        return "OK"
    }

    private fun printCRT(crt: Array<CharArray>) {
        for (i in 0 until CRT_NUM_ROWS) {
            for (j in 0 until CRT_NUM_COLUMNS) {
                print(crt[i][j])
            }
            println()
        }
    }
}
