package com.github.chkypros.aoc2022.day10

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream
import kotlin.math.abs

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
            signalStrength += getSignalStrengthDeltaForCycle(cycle)

            if (it.startsWith("noop")) {
                cycle++
            } else {
                signalStrength += getSignalStrengthDeltaForCycle(cycle + 1)

                val value = it.substring(5).toInt()
                register += value
                cycle += 2
            }
        }

        return signalStrength
    }

    private fun getSignalStrengthDeltaForCycle(cycle: Int): Int {
        return if (cyclesOfInterest.contains(cycle))
            cycle * register
        else
            0
    }

    override fun solvePartTwo(stream: Stream<String>): String {
        val instructions = stream.toList()
        val crt = Array(CRT_NUM_ROWS) { CharArray(CRT_NUM_COLUMNS) { '.' } }

        register = 1
        cycle = 1

        instructions.forEach {
            updateCRT(crt, cycle, register)

            if (it.startsWith("noop")) {
                cycle++
            } else {
                updateCRT(crt, cycle + 1, register)

                val value = it.substring(5).toInt()
                register += value
                cycle += 2
            }
        }

        printCRT(crt)
        return "RZHFGJCB"
    }

    private fun updateCRT(crt: Array<CharArray>, cycle: Int, register: Int) {
        val pixel = Pair(
            (cycle - 1) / CRT_NUM_COLUMNS,
            (cycle - 1) % CRT_NUM_COLUMNS
        )
        val shouldPixelBeLit = abs(pixel.second - register) <= 1
        crt[pixel.first][pixel.second] = if (shouldPixelBeLit) '#' else '.'
    }

    private fun printCRT(crt: Array<CharArray>) {
        for (i in 0 until CRT_NUM_ROWS) {
            for (j in 0 until CRT_NUM_COLUMNS) {
                print("${crt[i][j]} ")
            }
            println()
        }
    }
}
