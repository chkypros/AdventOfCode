package com.github.chkypros.aoc2022.day9

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream
import kotlin.math.abs

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
class RopeBridge : AbstractSolution() {
    // Head and Tail start at 0,0 (visited by default)
    var headPosition = Pair(0, 0)
    var tailPosition = Pair(0, 0)
    val tailTrailer = TailTrailer()

    override fun solvePartOne(stream: Stream<String>): Int {
        stream
            .map { Step(it) }
            .forEach {
                for (i in 0 until it.count) {
                    updateHeadPosition(it.step)
                    updateTailPosition()
                    tailTrailer.markPosition(tailPosition)
                }
            }

        return tailTrailer.visitedPositions.size
    }

    private fun updateHeadPosition(step: Pair<Int, Int>) {
        headPosition = Pair(
            headPosition.first + step.first,
            headPosition.second + step.second
        )
    }

    private fun updateTailPosition() {
        val verticalDistance = headPosition.first - tailPosition.first
        val horizontalDistance = headPosition.second - tailPosition.second
        val absoluteVerticalDistance = abs(verticalDistance)
        val absoluteHorizontalDistance = abs(horizontalDistance)
        if (absoluteVerticalDistance > 1 || absoluteHorizontalDistance > 1) {
            var verticalPosition = tailPosition.first
            var horizontalPosition = tailPosition.second

            if (absoluteVerticalDistance > 0) {
                verticalPosition += if (verticalDistance > 0) 1 else -1
            }
            if (absoluteHorizontalDistance > 0) {
                horizontalPosition += if (horizontalDistance > 0) 1 else -1
            }

            tailPosition = Pair(verticalPosition, horizontalPosition)
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
