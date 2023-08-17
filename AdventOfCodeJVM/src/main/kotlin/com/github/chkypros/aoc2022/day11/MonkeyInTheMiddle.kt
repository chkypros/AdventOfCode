package com.github.chkypros.aoc2022.day11

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream

class MonkeyInTheMiddle: AbstractSolution() {
    override fun solvePartOne(stream: Stream<String>): Long {
        return solveForRoundsWithRelief(stream, 20)
        { item -> item / 3 }
    }

    override fun solvePartTwo(stream: Stream<String>): Long {
        return solveForRoundsWithRelief(stream, 10_000)
        { item -> item }
    }

    private fun solveForRoundsWithRelief(
        stream: Stream<String>,
        rounds: Long,
        relief: (Long) -> Long
    ): Long {
        val monkeys = parseNotes(stream)
        val divisorsProduct = monkeys.map { it.testDivisor }.reduce {x, y -> x * y}

        for (r in 0 until rounds) {
            for (m in monkeys) {
                while (m.items.isNotEmpty()) {
                    var item = m.items.removeFirst()
                    item = m.operation(item)
                    item = relief(item)
                    item %= divisorsProduct
                    val nextMonkey = m.toMonkey[m.test(item)]
                    monkeys[nextMonkey!!].items.add(item)
                }
            }
        }

        val monkeyInspections = monkeys.map { it.inspections.toLong() }.sorted().reversed()
        return monkeyInspections[0] * monkeyInspections[1]
    }

    private fun parseNotes(stream: Stream<String>): List<Monkey> {
        val descriptionsLine = stream.reduce { x, y -> "$x\n$y" }.get()
        return descriptionsLine.split("\n\n")
            .map { Monkey(it) }
    }

    class Monkey(description: String) {
        val operation: (Long) -> Long
        val test: (Long) -> Boolean
        val testDivisor: Long
        val toMonkey = HashMap<Boolean, Int>()

        var items: MutableList<Long>
        var inspections = 0

        init {
            val descriptionLines = description.split("\n")
            // region Setup Starting Items
            items = descriptionLines[1]
                .substring(descriptionLines[1].indexOf(':') + 1)
                .split(',', ' ')
                .filter { it.isNotBlank() }
                .map { it.toLong() }
                .toMutableList()
            // endregion

            // region Setup Operation
            val operationsLine = descriptionLines[2]
                .substring(descriptionLines[2].indexOf(":") + 1)
                .split(' ')
                .filter { it.isNotBlank() }

            val isAddition = operationsLine[3] == "+"
            val isSecondArgumentOld = operationsLine[4] == "old"
            operation = if (isAddition) {
                { old ->
                        inspections++
                        old + if (isSecondArgumentOld) old else operationsLine[4].toLong()
                }
            } else {
                { old ->
                    inspections++
                    old * if (isSecondArgumentOld) old else operationsLine[4].toLong()
                }
            }
            // endregion

            // region Setup test
            testDivisor = descriptionLines[3].split(' ').filter { it.isNotBlank() }[3].toLong()
            test = { value -> 0L == value % testDivisor }
            // endregion

            // region Setup Monkeys to throw
            toMonkey[true] = descriptionLines[4].split(' ').filter { it.isNotBlank() }[5].toInt()
            toMonkey[false] = descriptionLines[5].split(' ').filter { it.isNotBlank() }[5].toInt()
            // endregion
        }
    }
}
