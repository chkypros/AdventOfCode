package com.github.chkypros.aoc2022.day17

import com.github.chkypros.aoc_template.AbstractSolution
import com.github.chkypros.aoc_common.Point
import java.util.stream.Stream
import kotlin.math.max

private const val MAX_CHAMBER_DEPTH = 1_000_000
private const val CHAMBER_WIDTH = 7

class PyroclasticFlow : AbstractSolution() {

    private val rockSequence = RepeatingSequence(Rock.values())
    private val chamber = Chamber()

    private lateinit var rockPosition: Point

    override fun solvePartOne(stream: Stream<String>): Int {
        val jetPattern = stream.findFirst().get()
        val jetSequence = RepeatingSequence(jetPattern.toCharArray().toTypedArray())

        rockPosition = Point(3, 2)
        var rocksStopped = 0
        var jetDirection = jetSequence.get()
        var maxHeight = 0

        while (rocksStopped < 2022) {
            applyJetMovement(jetDirection)
            jetDirection = jetSequence.next()

            if (!moveRockDown()) {
                rocksStopped++
                chamber.markStoppedRock(rockPosition, rockSequence.get())

                maxHeight = max(maxHeight, rockPosition.row + 1)
                rockPosition = Point(
                    maxHeight + rockSequence.next().height + 2,
                    2
                )
            }
        }

        return maxHeight
    }

    private fun applyJetMovement(jetDirection: Char) {
        if (jetDirection == '<') {
            if (rockSequence.get().canMoveLeft(rockPosition, chamber)) {
                rockPosition = rockPosition.add(0, -1)
            }
        } else {
            if (rockSequence.get().canMoveRight(rockPosition, chamber)) {
                rockPosition = rockPosition.add(0, 1)
            }
        }
    }

    private fun moveRockDown(): Boolean {
        if (rockSequence.get().canMoveDown(rockPosition, chamber)) {
            rockPosition = rockPosition.add(-1, 0)

            return true
        }

        return false
    }

    enum class Rock(val width: Int, val height: Int, points: IntArray) {
        LINE(4, 1, intArrayOf(0, 1, 2, 3)),
        CROSS(3, 3, intArrayOf(1, 3, 4, 5, 7)),
        CORNER(3, 3, intArrayOf(2, 5, 6, 7, 8)),
        PIPE(1, 4, intArrayOf(0, 1, 2, 3)),
        BLOCK(2, 2, intArrayOf(0, 1, 2, 3));

        private val leftCheckPoints = mutableSetOf<Point>()
        private val rightCheckPoints = mutableSetOf<Point>()
        private val downCheckPoints = mutableSetOf<Point>()

        val grid = Array(height) { BooleanArray(width) }

        init {
            points.forEach {
                val x = it / width
                val y = it % width
                grid[x][y] = true
            }

            for (x in 0 until height) {
                for (y in 0 until width) {
                    if (grid[x][y]) {
                        leftCheckPoints.add(Point(-x, y))
                        break
                    }
                }
            }

            for (x in 0 until height) {
                for (y in width - 1 downTo 0) {
                    if (grid[x][y]) {
                        rightCheckPoints.add(Point(-x, y))
                        break
                    }
                }
            }

            for (y in 0 until width) {
                for (x in height - 1 downTo 0) {
                    if (grid[x][y]) {
                        downCheckPoints.add(Point(-x, y))
                        break
                    }
                }
            }
        }

        fun canMoveLeft(position: Point, chamber: Chamber): Boolean =
            leftCheckPoints.all { chamber.isPointFree(position.add(it).add(0, -1)) }

        fun canMoveRight(position: Point, chamber: Chamber): Boolean =
            rightCheckPoints.all { chamber.isPointFree(position.add(it).add(0, 1)) }

        fun canMoveDown(position: Point, chamber: Chamber): Boolean =
            downCheckPoints.all { chamber.isPointFree(position.add(it).add(-1, 0)) }
    }

    class Chamber {
        private val chamber = List(MAX_CHAMBER_DEPTH) { BooleanArray(CHAMBER_WIDTH) }

        fun isPointFree(point: Point): Boolean =
            point.column in 0 until CHAMBER_WIDTH && point.row >= 0 && !chamber[point.row][point.column]

        fun markStoppedRock(rockPosition: Point, rock: Rock) {
            for (x in 0 until rock.height) {
                for (y in 0 until rock.width) {
                    if (rock.grid[x][y]) {
                        val pointToMark = rockPosition.add(-x, y)
                        chamber[pointToMark.row][pointToMark.column] = true
                    }
                }
            }
        }
    }

    class RepeatingSequence<T>(private val data: Array<T>) {
        var index = 0

        fun get(): T = data[index]

        fun next(): T {
            index = (index + 1) % data.size
            return data[index]
        }
    }
}
