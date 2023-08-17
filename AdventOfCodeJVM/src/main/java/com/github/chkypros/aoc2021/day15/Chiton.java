package com.github.chkypros.aoc2021.day15;

import com.github.chkypros.aoc2021.day5.Point;
import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chiton extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        Node[][] graph = parseNodes(stream);
        Point source = Point.of(0, 0);

        final Dijkstra dijkstra = new Dijkstra(graph, source);
        dijkstra.findShortestPaths();

        Point destination = Point.of(graph.length - 1, graph[0].length - 1);
        return dijkstra.getDistanceOf(destination);
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        Node[][] graph = parseNodes(stream);
        Point source = Point.of(0, 0);

        Node[][] extrapolatedGraph = extrapolateEnhancedGraph(graph);
        final Dijkstra dijkstra = new Dijkstra(extrapolatedGraph, source);
        dijkstra.findShortestPaths();

        Point destination = Point.of(extrapolatedGraph.length - 1, extrapolatedGraph[0].length - 1);
        return dijkstra.getDistanceOf(destination);
    }

    private Node[][] parseNodes(Stream<String> stream) {
        final List<String> inputLines = stream.collect(Collectors.toList());
        Node[][] nodes = new Node[inputLines.size()][inputLines.get(0).length()];

        for (int i = 0; i < inputLines.size(); i++) {
            final char[] digits = inputLines.get(i).toCharArray();
            for (int j = 0; j < digits.length; j++) {
                final int risk = Character.getNumericValue(digits[j]);

                final Node node = Node.atPosition(i, j, risk);
                nodes[i][j] = node;

                addNeighbours(node, nodes);
            }
        }

        return nodes;
    }

    private void addNeighbours(Node node, Node[][] nodes) {
        addNeighbour(node, nodes, -1, 0);
        addNeighbour(node, nodes, 0, -1);
    }

    private void addNeighbour(Node node, Node[][] nodes, int xOffset, int yOffset) {
        final Point neighbourPosition = Point.of(node.getPosition().x + xOffset, node.getPosition().y + yOffset);
        if (0 > neighbourPosition.x || 0 > neighbourPosition.y) {
            return;
        }

        final Node neighbour = nodes[neighbourPosition.x][neighbourPosition.y];

        node.addNeighbour(neighbour);
        neighbour.addNeighbour(node);
    }

    private Node[][] extrapolateEnhancedGraph(Node[][] graph) {
        final int originalHeight = graph.length;
        final int originalWidth = graph[0].length;
        final Node[][] extrapolatedGraph = new Node[originalHeight * 5][originalWidth * 5];

        for (int i = 0; i < extrapolatedGraph.length; i++) {
            for (int j = 0; j < extrapolatedGraph[0].length; j++) {
                final int originalXIndex = i % originalHeight;
                final int originalYIndex = j % originalWidth;
                final int horizontalScale = i / originalHeight;
                final int verticalScale = j / originalWidth;

                final int originalRisk = graph[originalXIndex][originalYIndex].getRisk();
                int extrapolatedRisk = originalRisk + horizontalScale + verticalScale;
                extrapolatedRisk = extrapolatedRisk % 10 + extrapolatedRisk / 10;

                final Node node = Node.atPosition(i, j, extrapolatedRisk);
                extrapolatedGraph[i][j] = node;

                addNeighbours(node, extrapolatedGraph);
            }
        }

        return extrapolatedGraph;
    }

}
