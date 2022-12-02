package com.github.chkypros.aoc2021.day15;

import com.github.chkypros.aoc2021.day5.Point;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Dijkstra {
    private final Set<Node> queue = new HashSet<>();
    private final long[][] totalRisk;

    public Dijkstra(Node[][] nodes, Point source) {

        this.totalRisk = new long[nodes.length][nodes[0].length];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                this.totalRisk[i][j] = Integer.MAX_VALUE;
                this.queue.add(nodes[i][j]);
            }
        }
        this.totalRisk[source.x][source.y] = 0;
    }

    public void findShortestPaths() {
        while (!queue.isEmpty()) {
            Node minTotalRiskNode = getNextMinTotalRiskNode();
            queue.remove(minTotalRiskNode);

            minTotalRiskNode.getNeighbours()
                .forEach(node -> updateTotalRisk(node, minTotalRiskNode));
        }
    }

    private Node getNextMinTotalRiskNode() {
        return queue.stream().min(Comparator.comparingLong(this::getTotalRisk)).get();
    }

    private void updateTotalRisk(Node node, Node minTotalRiskNode) {
        long alternativeRisk = getTotalRisk(minTotalRiskNode) + node.getRisk();
        if (alternativeRisk < getTotalRisk(node)) {
            setTotalRisk(node, alternativeRisk);
        }
    }

    private long getTotalRisk(Node node) {
        return totalRisk[node.getPosition().x][node.getPosition().y];
    }

    private void setTotalRisk(Node node, long risk) {
        totalRisk[node.getPosition().x][node.getPosition().y] = risk;
    }

    public Long getDistanceOf(Point destination) {
        return totalRisk[destination.x][destination.y];
    }

    // Dijkstra's pseudo-code shamelessly copied from https://brilliant.org/wiki/dijkstras-short-path-finder/
/*
function Dijkstra(Graph, source):
       dist[source]  := 0                     // Distance from source to source is set to 0
       for each vertex v in Graph:            // Initializations
           if v ≠ source
               dist[v]  := infinity           // Unknown distance function from source to each node set to infinity
           add v to Q                         // All nodes initially in Q

      while Q is not empty:                  // The main loop
          v := vertex in Q with min dist[v]  // In the first run-through, this vertex is the source node
          remove v from Q

          for each neighbor u of v:           // where neighbor u has not yet been removed from Q.
              alt := dist[v] + length(v, u)
              if alt < dist[u]:               // A shorter path to u has been found
                  dist[u]  := alt            // Update distance of u

      return dist[]
  end function
*/
}
