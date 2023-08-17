package com.github.chkypros.aoc2022.day15

import com.github.chkypros.aoc_template.AbstractSolution
import com.github.chkypros.aoc_common.Point
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Stream
import kotlin.math.abs

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

    override fun solvePartTwo(stream: Stream<String>): Long {
        val sensors = parseInput(stream)
        val outOfScopePoint = sensors.flatMap(this::getPointsOutsideRange)
// region Premature optimization causing java.lang.OutOfMemoryError: Java heap space
//            .groupBy { it }
//            .mapValues { it.value.size }
//            .toList()
//            .sortedBy { it.second }
//            .reversed()
// endregion
            .firstOrNull { !isInSensorsScopes(it, sensors) }
        return if (null == outOfScopePoint) -1 else outOfScopePoint.column.toLong() * 4_000_000 + outOfScopePoint.row
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

    private fun getPointsOutsideRange(sensor: Sensor): List<Point> {
        val pointsOutsideRange = mutableListOf<Point>()

        val outerDistance = sensor.distance + 1
        addPointRange(sensor, pointsOutsideRange, Pair(-outerDistance, 0), Pair(1, 1))
        addPointRange(sensor, pointsOutsideRange, Pair(0, outerDistance), Pair(1, -1))
        addPointRange(sensor, pointsOutsideRange, Pair(outerDistance, 0), Pair(-1, -1))
        addPointRange(sensor, pointsOutsideRange, Pair(0, -outerDistance), Pair(-1, 1))

        return pointsOutsideRange
    }

    private fun addPointRange(
        sensor: Sensor,
        points: MutableList<Point>,
        startingOffset: Pair<Int, Int>,
        step: Pair<Int, Int>
    ) {
        var point = Point(
            sensor.position.row + startingOffset.first,
            sensor.position.column + startingOffset.second
        )
        for (i in 0..sensor.distance) {
            if (point.row in 0..searchRange
                && point.column in 0..searchRange) {
                points.add(point)
            }
            point = Point(
                point.row + step.first,
                point.column + step.second
            )
        }
    }

    private fun isInSensorsScopes(point: Point, sensors: List<Sensor>): Boolean {
        for (sensor in sensors) {
            val rowOffset = sensor.position.row - point.row
            val rowDistance = abs(rowOffset)
            val manhattanDistance = rowDistance + abs(sensor.position.column - point.column)
            if (sensor.distance >= manhattanDistance) {
                return true
            }
        }

        return false
    }

    data class Sensor(val position: Point, val closestBeaconPosition: Point) {
        private val rowOffset = position.row - closestBeaconPosition.row
        private val columnOffset = position.column - closestBeaconPosition.column
        private val rowDistance = abs(rowOffset)
        private val columnDistance = abs(columnOffset)
        val distance = rowDistance + columnDistance
    }
}
