package com.github.chkypros.aoc2022.day12

import com.github.chkypros.aoc_template.AbstractSolution
import com.github.chkypros.aoc_template.Node
import com.github.chkypros.aoc_template.Point
import java.util.stream.Stream

class HillClimbingAlgorithm: AbstractSolution() {
    override fun solvePartOne(stream: Stream<String>): Int {
        val graph = parseInput(stream)
        return -1
    }

    private fun parseInput(stream: Stream<String>): Array<Array<Node>> {
        val map = stream.toList()
        val graph = Array(map.size) { arrayOfNulls<Node>(map[0].length) }

        for (i in 0 until map.size) {
            for (j in 0 until map[0].length) {
                val node = Node.atPosition(Point(i, j), 1)
                addNeighbours(node, graph, map)
                graph[i][j] = node
            }
        }

        return graph as Array<Array<Node>>
    }

    private fun addNeighbours(node: Node, graph: Array<Array<Node?>>, map: List<String>) {
        checkAndAddNeighbour(node, graph, -1, 0, map)
        checkAndAddNeighbour(node, graph, 0, -1, map)
        checkAndAddNeighbour(node, graph, 1, 0, map)
        checkAndAddNeighbour(node, graph, 0, 1, map)
    }

    private fun checkAndAddNeighbour(node: Node, graph: Array<Array<Node?>>, rowOffset: Int, columnOffset: Int, map: List<String>) {
        val neighbourRow = node.position.row + rowOffset
        val neighbourColumn = node.position.column + columnOffset

        var nodeLetter = map[node.position.row][node.position.column]
        var neighbourLetter = map[neighbourRow][neighbourColumn]

        nodeLetter = if (nodeLetter == 'S') 'a' else nodeLetter
        neighbourLetter = if (neighbourLetter == 'S') 'a' else if (neighbourLetter == 'E') 'z' else neighbourLetter

        if (nodeLetter <= neighbourLetter + 1) {
            node.addNeighbour(graph[neighbourRow][neighbourColumn])
        }
    }
}
