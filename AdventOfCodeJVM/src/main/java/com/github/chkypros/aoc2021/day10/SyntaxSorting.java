package com.github.chkypros.aoc2021.day10;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SyntaxSorting extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return stream
                .map(ChunkSequence::of)
                .filter(ChunkSequence::isCorrupted)
                .map(ChunkSequence::getIllegalBrace)
                .mapToLong(ScoreEvaluator::getIllegalBraceScore)
                .sum();
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final List<Long> scores = stream
                .map(ChunkSequence::of)
                .filter(ChunkSequence::isIncomplete)
                .map(ChunkSequence::getMissingClosingBraces)
                .mapToLong(ScoreEvaluator::getMissingBracesScore)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        return scores.get(scores.size() / 2);
    }
}
