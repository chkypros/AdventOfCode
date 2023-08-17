package com.github.chkypros.aoc2022.day8

import com.github.chkypros.aoc_template.AbstractSolution
import java.util.stream.Stream

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
class TreetopTreeHouse : AbstractSolution() {
    override fun solvePartOne(stream: Stream<String>): Int {
        val forest = constructForest(stream)
        return getTreePositions(forest)
            .count { isTreeVisible(it, forest) }
    }

    private fun constructForest(stream: Stream<String>): Array<IntArray> {
        val input = stream.toList()
        val forest = Array(input.size) { IntArray(input[0].length) }
        for (i in 0 until forest.size) {
            for (j in 0 until forest[0].size) {
                forest[i][j] = input[i][j].digitToInt()
            }
        }
        return forest
    }

    private fun getTreePositions(forest: Array<IntArray>) =
        IntRange(0, forest.size * forest[0].size - 1)
            .map { Pair(it / forest.size, it % forest.size) }

    override fun solvePartTwo(stream: Stream<String>): Int {
        val forest = constructForest(stream)
        return getTreePositions(forest)
            .maxOf { calculateTreeScenicScore(it, forest) }
    }

    private fun isTreeVisible(tree: Pair<Int, Int>, forest: Array<IntArray>): Boolean {
        return isTreeVisibleWithStep(tree, forest, Pair(1, 0)) ||
            isTreeVisibleWithStep(tree, forest, Pair(0, 1)) ||
            isTreeVisibleWithStep(tree, forest, Pair(-1, 0)) ||
            isTreeVisibleWithStep(tree, forest, Pair(0, -1))
    }

    private fun isTreeVisibleWithStep(tree: Pair<Int, Int>, forest: Array<IntArray>, step: Pair<Int, Int>): Boolean {
        var offset = 1
        var offsetRowIndex = offset * step.first + tree.first
        var offsetColumnIndex = offset * step.second + tree.second

        while (offsetRowIndex >= 0 && offsetRowIndex < forest.size
            && offsetColumnIndex >= 0 && offsetColumnIndex < forest[0].size
        ) {
            if (forest[offsetRowIndex][offsetColumnIndex] >= forest[tree.first][tree.second]) {
                return false
            }

            offset++
            offsetRowIndex = offset * step.first + tree.first
            offsetColumnIndex = offset * step.second + tree.second
        }

        return true
    }

    private fun calculateTreeScenicScore(tree: Pair<Int, Int>, forest: Array<IntArray>): Int {
        return countVisibleTreesWithStep(tree, forest, Pair(1, 0)) *
            countVisibleTreesWithStep(tree, forest, Pair(0, 1)) *
            countVisibleTreesWithStep(tree, forest, Pair(-1, 0)) *
            countVisibleTreesWithStep(tree, forest, Pair(0, -1))
    }

    private fun countVisibleTreesWithStep(tree: Pair<Int, Int>, forest: Array<IntArray>, step: Pair<Int, Int>): Int {
        var visibleTreesCount = 0
        var offset = 1
        var offsetRowIndex = offset * step.first + tree.first
        var offsetColumnIndex = offset * step.second + tree.second

        while (offsetRowIndex >= 0 && offsetRowIndex < forest.size
            && offsetColumnIndex >= 0 && offsetColumnIndex < forest[0].size
        ) {
            visibleTreesCount++
            if (forest[offsetRowIndex][offsetColumnIndex] >= forest[tree.first][tree.second]) {
                break
            }

            offset++
            offsetRowIndex = offset * step.first + tree.first
            offsetColumnIndex = offset * step.second + tree.second
        }

        return visibleTreesCount
    }
}
