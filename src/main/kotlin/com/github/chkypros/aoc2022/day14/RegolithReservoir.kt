package com.github.chkypros.aoc2022.day14

import com.github.chkypros.aoc_template.AbstractSolution
import com.github.chkypros.aoc_template.Point
import java.util.stream.IntStream
import java.util.stream.Stream

class RegolithReservoir: AbstractSolution() {
    private var maxDepth = 0

    override fun solvePartOne(stream: Stream<String>): Int {
        maxDepth = 0
        val cave = parseInput(stream)
        return calculateRestingSands(cave)
    }

    override fun solvePartTwo(stream: Stream<String>): Int {
        maxDepth = 0
        val cave = parseInput(stream)
        maxDepth += 2
        simulateInfiniteGround(cave)
        return calculateRestingSands(cave)
    }

    private fun parseInput(input: Stream<String>): MutableMap<Int, CharArray> {
        val rockPaths = input.flatMap(this::getRanges).toList()
        val cave = mutableMapOf<Int, CharArray>()
        for (rockPath in rockPaths) {
            val start = rockPath.first
            val end = rockPath.second
            val stepColumn = if (start.column == end.column) 0 else if (start.column < end.column) 1 else -1
            val stepRow = if (start.row == end.row) 0 else if (start.row < end.row) 1 else -1

            var i = start.row
            var j = start.column

            while (i != end.row || j != end.column) {
                setRockAtPoint(cave, j, i)
                i += stepRow
                j += stepColumn
            }

            setRockAtPoint(cave, j, i)
        }

        return cave
    }

    private fun getRanges(rockFormation: String): Stream<Pair<Point, Point>> {
        val linePoints = rockFormation.split(" -> ")
        return IntStream.range(0, linePoints.size - 1)
            .mapToObj { Pair(getPoint(linePoints[it]), getPoint(linePoints[it + 1])) }
    }

    private fun getPoint(coordinates: String): Point {
        val coordinateValues = coordinates.split(",")
        val depth = coordinateValues[1].toInt()

        if (maxDepth < depth) maxDepth = depth

        return Point(depth, coordinateValues[0].toInt())
    }

    private fun setRockAtPoint(cave: MutableMap<Int, CharArray>, j: Int, i: Int) {
        if (!cave.containsKey(j)) {
            cave[j] = CharArray(maxDepth + 3) { ' ' }
        }
        cave[j]!![i] = '#'
    }

    private fun calculateRestingSands(cave: MutableMap<Int, CharArray>): Int {
        var restingSands = 0

        while (true) {
            var i = 0
            var j = 500

            while (i < maxDepth) {
                if (isCavePointEmpty(cave, j, i + 1)) {
                    i++
                } else if (isCavePointEmpty(cave, j - 1, i + 1)) {
                    i++
                    j--
                } else if (isCavePointEmpty(cave, j + 1, i + 1)) {
                    i++
                    j++
                } else {
                    break
                }
            }

            if (i == maxDepth) {
                break
            }

            cave[j]!![i] = 'o'
            restingSands++

            if (i == 0 && j == 500) {
                break
            }
        }

        return restingSands
    }

    private fun isCavePointEmpty(
        cave: MutableMap<Int, CharArray>,
        j: Int,
        i: Int
    ) = !cave.containsKey(j) || cave[j]!![i] == ' '

    private fun simulateInfiniteGround(cave: MutableMap<Int, CharArray>) {
        for (j in 0 until 1000) {
            setRockAtPoint(cave, j, maxDepth)
        }
    }
}
