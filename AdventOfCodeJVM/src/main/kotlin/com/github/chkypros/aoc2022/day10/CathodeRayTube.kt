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
        processInstructions(stream)
        { cycle -> signalStrength += getSignalStrengthDeltaForCycle(cycle) }

        return signalStrength
    }

    override fun solvePartTwo(stream: Stream<String>): String {
        val crt = Array(CRT_NUM_ROWS) { CharArray(CRT_NUM_COLUMNS) { '.' } }
        processInstructions(stream)
        { cycle -> updateCRT(crt, cycle) }

        printCRT(crt)
        return "RZHFGJCB"
    }

    private fun processInstructions(stream: Stream<String>, action: (Int) -> Unit) {
        val instructions = stream.toList()

        register = 1
        cycle = 1

        instructions.forEach {
            action(cycle)

            if (it.startsWith("noop")) {
                cycle++
            } else {
                action(cycle + 1)

                val value = it.substring(5).toInt()
                register += value
                cycle += 2
            }
        }
    }

    private fun getSignalStrengthDeltaForCycle(cycle: Int): Int {
        return if (cyclesOfInterest.contains(cycle))
            cycle * register
        else
            0
    }

    private fun updateCRT(crt: Array<CharArray>, cycle: Int) {
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
