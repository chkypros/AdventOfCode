package com.github.chkypros.aoc2020.day15;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class RambuctiousRecitation extends AbstractSolution {
    public static void main(String[] args) throws Exception {
        new RambuctiousRecitation().solve();
    }

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return getNthRecitedNumber(stream, 2020);
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        return getNthRecitedNumber(stream, 30000000);
    }

    private Long getNthRecitedNumber(Stream<String> stream, long target) {
        final List<Long> startingNumbers = stream
            .flatMap(s -> Arrays.stream(s.split(",")))
            .map(Long::parseLong)
            .collect(Collectors.toList());
        final Map<Long, Pair> recited = new HashMap<>();
        long i = 1;
        long last = -1;

        for (Long startingNumber : startingNumbers) {
            recited.put(startingNumber, new Pair(i++, -1));
            last = startingNumber;
        }

        while (i < target + 1) {
            if (recited.get(last).previousTurn != -1) {
                last = recited.get(last).diff();
            } else {
                last = 0L;
            }
            recited.putIfAbsent(last, new Pair(-1, -1));
            recited.get(last).push(i++);
        }

        return last;
    }

    static class Pair {
        long lastTurn;
        long previousTurn;
        Pair(long lastTurn, long previousTurn) { this.lastTurn = lastTurn; this.previousTurn = previousTurn; }
        void push(long newTurn) { previousTurn = lastTurn; lastTurn = newTurn; }
        long diff() { return lastTurn - previousTurn; }
    }
}
