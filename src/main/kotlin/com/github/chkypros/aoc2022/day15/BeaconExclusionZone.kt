package com.github.chkypros.aoc2022.day15

import com.github.chkypros.aoc_template.AbstractSolution
import com.github.chkypros.aoc_template.Point
import java.time.LocalDateTime
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Stream
import kotlin.math.abs
import kotlin.math.max

class BeaconExclusionZone : AbstractSolution() {
    private val sensorDescriptionPattern: Pattern = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)")

    var rowOfInterest = 2_000_000
    var searchRange = 4_000_000

    override fun solvePartOne(stream: Stream<String>): Int {
        val sensors = parseInput(stream)
        val clearPositions = HashSet<Point>()
        val occupiedPositions = HashSet<Point>()

        sensors.forEach {
            addPointsOfInterestToSet(occupiedPositions, it.position, it.closestBeaconPosition)
            val clearPositionsFromSensor = getClearPositionsFromSensor(it, occupiedPositions)
            addPointsOfInterestToSet(clearPositions, *clearPositionsFromSensor.toTypedArray())
        }

        return clearPositions.size
    }

    override fun solvePartTwo(stream: Stream<String>): Int {
        val sensors = parseInput(stream)
        for (x in 0..searchRange) {
            if (x % 1_000 == 0) println("Checking x = $x " + LocalDateTime.now().toString())
            var y = 0
            coordinate@ while (y <= searchRange) {
//                if (y % 1_000_000 == 0) println("  Checking y = $y")
                for (sensor in sensors) {
                    val rowOffset = sensor.position.row - y
                    val rowDistance = abs(rowOffset)
                    val manhattanDistance = rowDistance + abs(sensor.position.column - x)
                    if (sensor.distance >= manhattanDistance) {
                        y += max(2 * rowOffset - 1, 1)
                        continue@coordinate
                    }
                }

                return x * 4_000_000 + y
            }
        }
        return -1
    }

    private fun parseInput(stream: Stream<String>): List<Sensor> = stream.map(this::getSensor).toList()

    private fun getSensor(description: String): Sensor {
        val matcher = sensorDescriptionPattern.matcher(description)
        matcher.find()

        return Sensor(
            Point(getIntGroup(matcher, 2), getIntGroup(matcher, 1)),
            Point(getIntGroup(matcher, 4), getIntGroup(matcher, 3))
        )
    }

    private fun getIntGroup(matcher: Matcher, index: Int) = matcher.group(index).toInt()

    private fun addPointsOfInterestToSet(positionsOfInterest: MutableSet<Point>, vararg positionsToAdd: Point) {
        positionsToAdd.filter { it.row == rowOfInterest }
            .forEach(positionsOfInterest::add)
    }

    private fun getClearPositionsFromSensor(
        sensor: Sensor,
        occupiedPositions: Set<Point>
    ): Set<Point> {
        val rowOfInterestOffset = sensor.position.row - rowOfInterest
        val rowOfInterestDistance = abs(rowOfInterestOffset)
        val columnDistanceOfInterest = sensor.distance - rowOfInterestDistance

        return IntRange(-columnDistanceOfInterest, columnDistanceOfInterest)
            .map { sensor.position.column + it }
            .map { Point(rowOfInterest, it) }
            .filter { !occupiedPositions.contains(it) }
            .toSet()
    }

    data class Sensor(val position: Point, val closestBeaconPosition: Point) {
        val rowOffset: Int
        val columnOffset: Int
        val rowDistance: Int
        val columnDistance: Int
        val distance: Int

        init {
            rowOffset = position.row - closestBeaconPosition.row
            columnOffset = position.column - closestBeaconPosition.column
            rowDistance = abs(rowOffset)
            columnDistance = abs(columnOffset)
            distance = rowDistance + columnDistance

        }
    }
}
