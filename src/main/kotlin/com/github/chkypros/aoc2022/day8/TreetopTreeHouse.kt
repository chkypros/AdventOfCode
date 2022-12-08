package com.github.chkypros.aoc2022.day8

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
class TreetopTreeHouse : AbstractSolution() {
    override fun solvePartOne(stream: Stream<String>): Int {
        val input = stream.toList()
        val forest = Array(input.size) { IntArray(input[0].length) }

        return IntRange(0, forest.size * forest[0].size)
            .map { Pair(it / forest.size, it % forest.size) }
            .map { isTreeVisible(it, forest) }
            .filter { it }
            .count()
    }

    private fun isTreeVisible(tree: Pair<Int, Int>, forest: Array<IntArray>): Boolean {
        return isTreeVisibleWithStep(tree, forest, Pair(1, 0))
                || isTreeVisibleWithStep(tree, forest, Pair(0, 1))
                || isTreeVisibleWithStep(tree, forest, Pair(-1, 0))
                || isTreeVisibleWithStep(tree, forest, Pair(0, -1))
    }

    private fun isTreeVisibleWithStep(tree: Pair<Int, Int>, forest: Array<IntArray>, step: Pair<Int, Int>): Boolean {
        var offset = 1
        var offsetRowIndex = offset * step.first + tree.first
        var offsetColumnIndex = offset * step.second + tree.second
        while (offsetRowIndex >= 0 && offsetRowIndex < forest.size
            && offsetColumnIndex >= 0 && offsetColumnIndex < forest[0].size
        ) {
            if (9 == forest[offsetRowIndex][offsetColumnIndex]
                || forest[offsetRowIndex][offsetColumnIndex] >= forest[tree.first][tree.second]
            ) {
                return false
            }

            offset++
            offsetRowIndex = offset * step.first + tree.first
            offsetColumnIndex = offset * step.second + tree.second
        }

        return true
    }
}
