package com.github.chkypros.aoc2022.day13

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream
import kotlin.math.min

class DistressSignal: AbstractSolution() {
    override fun solvePartOne(stream: Stream<String>): Int {
        return parseInput(stream)
            .filter(this::isPairOrdered)
            .sumOf { it.index }
    }

    override fun solvePartTwo(stream: Stream<String>): Int {
        val packets = parseInput(stream)
            .flatMap { listOf(it.first, it.second) }
            .toMutableList()
        packets.addAll(listOf(
            getPacket("[[2]]"),
            getPacket("[[6]]")
        ))
        packets.sortWith {first, second ->
            val orderedResult = areValuesOrdered(first, second)
            if (orderedResult == null) 0 else if (orderedResult) -1 else 1
        }

        val dividers = packets.mapIndexed {index, packet -> Pair(index + 1, packet)}
            .filter { listOf("[[2]]", "[[6]]").contains(it.second.description) }
        return dividers[0].first * dividers[1].first
    }

    private fun parseInput(stream: Stream<String>): List<IndexedPair> {
        val descriptionsLine = stream.reduce { x, y -> "$x\n$y" }.get()
        return descriptionsLine.split("\n\n")
            .mapIndexed { index, it ->
                val parts = it.split("\n")
                IndexedPair(index + 1, getPacket(parts[0]), getPacket(parts[1]))
            }
    }

    private fun isPairOrdered(pair: IndexedPair): Boolean = areValuesOrdered(pair.first, pair.second)?: true
    private fun getPacket(description: String): ListValue {
        val packet = getSubPacket(description, 0)!!.second as ListValue
        packet.description = description
        return packet
    }

    private fun getSubPacket(description: String, startIndex: Int): Pair<Int, Value<Any>>? {
        if (description[startIndex] == '[') {
            val subPackets = mutableListOf<Value<Any>>()
            var index = startIndex + 1
            var endIndex = index
            do {
                val subPacket = getSubPacket(description, index)
                if (subPacket != null) {
                    subPackets.add(subPacket.second)
                    endIndex = subPacket.first
                    index = subPacket.first + 1
                }
            } while (description[endIndex] != ']')
            return Pair(endIndex + 1, ListValue(subPackets))
        } else if (description[startIndex] == ']') {
            return null
        } else {
            val endIndex = description.indexOfAny(charArrayOf(']', ','), startIndex + 1)
            return Pair(endIndex, IntegerValue(description.substring(startIndex, endIndex).toInt()))
        }
    }

    private fun areValuesOrdered(firstPacket: IntegerValue, secondPacket: IntegerValue): Boolean? =
        areIntegersOrdered(firstPacket.data, secondPacket.data)

    private fun areIntegersOrdered(first: Int, second: Int): Boolean? =
        if (first == second) null else first < second

    private fun areValuesOrdered(firstPacket: ListValue, secondPacket: ListValue): Boolean? {
        val itemsToCheck = min(firstPacket.data.size, secondPacket.data.size)
        for (i in 0 until itemsToCheck) {
            val firstSubPacket = firstPacket.data[i]
            val secondSubPacket = secondPacket.data[i]
            val isFirstSubPacketInteger = firstSubPacket is IntegerValue
            val isSecondSubPacketInteger = secondSubPacket is IntegerValue

            val subResult = when {
                isFirstSubPacketInteger && isSecondSubPacketInteger -> areValuesOrdered(firstSubPacket as IntegerValue, secondSubPacket as IntegerValue)
                isFirstSubPacketInteger -> areValuesOrdered(firstSubPacket as IntegerValue, secondSubPacket as ListValue)
                isSecondSubPacketInteger -> areValuesOrdered(firstSubPacket as ListValue, secondSubPacket as IntegerValue)
                else -> areValuesOrdered(firstSubPacket as ListValue, secondSubPacket as ListValue)
            }

            if (subResult != null) {
                return subResult
            }
        }

        return areIntegersOrdered(firstPacket.data.size, secondPacket.data.size)
    }

    private fun areValuesOrdered(firstPacket: IntegerValue, secondPacket: ListValue): Boolean? =
        areValuesOrdered(ListValue(listOf(firstPacket)), secondPacket)

    private fun areValuesOrdered(firstPacket: ListValue, secondPacket: IntegerValue): Boolean? =
        areValuesOrdered(firstPacket, ListValue(listOf(secondPacket)))

    class IndexedPair(val index: Int, val first: ListValue, val second: ListValue)
    abstract class Value<out T>(val isInteger: Boolean, val data: T)
    class IntegerValue(data: Int): Value<Int>(true, data)
    class ListValue(data: List<Value<Any>>, var description: String? = null): Value<List<Value<Any>>>(false, data)
}
