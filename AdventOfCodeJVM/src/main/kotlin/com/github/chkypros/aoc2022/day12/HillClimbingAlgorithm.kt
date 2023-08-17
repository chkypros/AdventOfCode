package com.github.chkypros.aoc2022.day12

import com.github.chkypros.aoc_template.AbstractSolution
import com.github.chkypros.aoc_common.Dijkstra
import com.github.chkypros.aoc_common.Node
import com.github.chkypros.aoc_common.Point
import java.util.function.BiPredicate
import java.util.stream.Stream

class HillClimbingAlgorithm: AbstractSolution() {
    override fun solvePartOne(stream: Stream<String>): Long {
        val graph = parseInput(stream) { nodeLetter: Char, neighbourLetter: Char -> nodeLetter >= neighbourLetter - 1 }
        val dijkstra = Dijkstra(graph.nodes, graph.source.position)
        dijkstra.findShortestPaths()
        return dijkstra.getDistanceOf(graph.destination.position)
    }

    override fun solvePartTwo(stream: Stream<String>): Long {
        val graph = parseInput(stream) { nodeLetter: Char, neighbourLetter: Char -> nodeLetter <= neighbourLetter + 1 }
        val dijkstra =
            Dijkstra(graph.nodes, graph.destination.position)
        dijkstra.findShortestPaths()

        var minSteps = graph.map.size * graph.map[0].length + 1L
        for (i in 0 until graph.map.size) {
            for (j in 0 until graph.map[0].length) {
                if (graph.map[i][j] == 'a' || graph.map[i][j] == 'S') {
                    val stepsToNode = dijkstra.getDistanceOf(
                        Point(
                            i,
                            j
                        )
                    )
                    if (minSteps > stepsToNode) {
                        minSteps = stepsToNode
                    }
                }
            }
        }

        return minSteps
    }

    private fun parseInput(stream: Stream<String>, addNeighbourPredicate: BiPredicate<Char, Char>): Graph {
        var source = Node.atPosition(-1, -1, 0)
        var destination = Node.atPosition(-1, -1, 0)
        val map = stream.toList()
        val graphInit = Array(map.size) { arrayOfNulls<Node>(map[0].length) }

        for (i in 0 until map.size) {
            for (j in 0 until map[0].length) {
                val node = Node.atPosition(
                    Point(
                        i,
                        j
                    ), 1)
                graphInit[i][j] = node

                if (map[i][j] == 'S') {
                    source = node
                } else if (map[i][j] == 'E') {
                    destination = node
                }
            }
        }

        val graph = graphInit as Array<Array<Node>>
        for (i in 0 until map.size) {
            for (j in 0 until map[0].length) {
                addNeighbours(graph[i][j], graph, map, addNeighbourPredicate)
            }
        }

        return Graph(map, graph, source, destination)
    }

    private fun addNeighbours(
        node: Node,
        graph: Array<Array<Node>>,
        map: List<String>,
        addNeighbourPredicate: BiPredicate<Char, Char>
    ) {
        checkAndAddNeighbour(node, graph, -1, 0, map, addNeighbourPredicate)
        checkAndAddNeighbour(node, graph, 0, -1, map, addNeighbourPredicate)
        checkAndAddNeighbour(node, graph, 1, 0, map, addNeighbourPredicate)
        checkAndAddNeighbour(node, graph, 0, 1, map, addNeighbourPredicate)
    }

    private fun checkAndAddNeighbour(
        node: Node, graph: Array<Array<Node>>,
        rowOffset: Int,
        columnOffset: Int,
        map: List<String>,
        addNeighbourPredicate: BiPredicate<Char, Char>
    ) {
        val neighbourRow = node.position.row + rowOffset
        val neighbourColumn = node.position.column + columnOffset

        val validRowCoordinateRange = IntRange(0, map.size - 1)
        val validColumnCoordinateRange = IntRange(0, map[0].length - 1)
        if (!validRowCoordinateRange.contains(neighbourRow) || !validColumnCoordinateRange.contains(neighbourColumn)) {
            return
        }

        val nodeLetter = getLetter(map[node.position.row][node.position.column])
        val neighbourLetter = getLetter(map[neighbourRow][neighbourColumn])

        if (addNeighbourPredicate.test(nodeLetter, neighbourLetter)) {
            node.addNeighbour(graph[neighbourRow][neighbourColumn])
        }
    }

    private fun getLetter(letter: Char) = if (letter == 'S') 'a' else if (letter == 'E') 'z' else letter

    class Graph(val map: MutableList<String>, val nodes: Array<Array<Node>>, val source: Node, val destination: Node)
}
