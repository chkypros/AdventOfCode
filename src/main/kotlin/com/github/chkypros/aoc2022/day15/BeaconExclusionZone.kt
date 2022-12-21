package com.github.chkypros.aoc2022.day15

import com.github.chkypros.aoc_template.AbstractSolution
import com.github.chkypros.aoc_template.Point
import java.util.regex.Pattern
import java.util.stream.Stream

class BeaconExclusionZone: AbstractSolution() {
    val SENSOR_DESCRIPTION_PATTERN = Pattern.compile("Sensor at x=(%d+), y=(%d+): closest beacon is at x=(%d+), y=(%d)")

    var rowOfInterest = 2_000_000

    override fun solvePartOne(stream: Stream<String>): Int {
        val sensors = parseInput(stream)
        return -1
    }

    private fun parseInput(stream: Stream<String>): List<Sensor> {
        stream.map(this::getSensor)
    }

    private fun getSensor(description: String): Sensor {
        val matcher = SENSOR_DESCRIPTION_PATTERN.matcher(description)
        matcher.find()

        return Sensor(Point(matcher.group(1), matcher.group(2)), Point(matcher.group(3), matcher.group(3)))
    }

    data class Sensor(val position: Point, val closestBeaconPosition: Point)
}
