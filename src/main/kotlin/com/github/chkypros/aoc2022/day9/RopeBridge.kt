package com.github.chkypros.aoc2022.day9

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream
import kotlin.math.abs

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
class RopeBridge : AbstractSolution() {
    lateinit var rope : Array<Pair<Int,Int>>
    lateinit var tailTrailer : TailTrailer

    override fun solvePartOne(stream: Stream<String>): Int {
        return solveForSize(2, stream)
    }

    private fun solveForSize(size: Int, stream: Stream<String>): Int {
        rope = IntRange(1, size).map { Pair(0, 0) }.toTypedArray()
        tailTrailer = TailTrailer()

        stream
            .map { Step(it) }
            .forEach {
                for (i in 0 until it.count) {
                    updateHeadPosition(it.step)
                    for (j in 1 until size) {
                        updateTailingPosition(j)
                    }
                    tailTrailer.markPosition(rope[size - 1])
                }
            }

        return tailTrailer.visitedPositions.size
    }

    override fun solvePartTwo(stream: Stream<String>): Int {
        return solveForSize(10, stream)
    }

    private fun updateHeadPosition(step: Pair<Int, Int>) {
        rope[0] = Pair(
            rope[0].first + step.first,
            rope[0].second + step.second
        )
    }

    private fun updateTailingPosition(knotIndex: Int) {
        val previousKnot = knotIndex - 1

        val verticalDistance = rope[previousKnot].first - rope[knotIndex].first
        val horizontalDistance = rope[previousKnot].second - rope[knotIndex].second

        val absoluteVerticalDistance = abs(verticalDistance)
        val absoluteHorizontalDistance = abs(horizontalDistance)

        if (absoluteVerticalDistance > 1 || absoluteHorizontalDistance > 1) {
            var verticalPosition = rope[knotIndex].first
            var horizontalPosition = rope[knotIndex].second

            if (absoluteVerticalDistance > 0) {
                verticalPosition += if (verticalDistance > 0) 1 else -1
            }
            if (absoluteHorizontalDistance > 0) {
                horizontalPosition += if (horizontalDistance > 0) 1 else -1
            }

            rope[knotIndex] = Pair(verticalPosition, horizontalPosition)
        }
    }

    class Step(description: String) {
        enum class Direction(verticalStep: Int, horizontalStep: Int) {
            U(1, 0),
            D(-1, 0),
            R(0, 1),
            L(0, -1);

            val step: Pair<Int,Int>

            init {
                step = Pair(verticalStep, horizontalStep)
            }
        }

        val step: Pair<Int,Int>
        val count: Int

        init {
            step = Direction.valueOf(description.substring(IntRange(0,0))).step
            count = description.substring(2).toInt()
        }
    }

    class TailTrailer {
        val visitedPositions = linkedSetOf(Pair(0,0))
        fun markPosition(position: Pair<Int,Int>) = visitedPositions.add(position)
    }
}
