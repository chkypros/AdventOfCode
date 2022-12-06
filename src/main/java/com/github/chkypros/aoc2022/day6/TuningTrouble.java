package com.github.chkypros.aoc2022.day6;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class TuningTrouble extends AbstractSolution {
    @Override
    public Object solvePartOne(Stream<String> stream) {
        var sequence = stream.flatMapToInt(String::chars)
            .mapToObj(c -> (char) c)
            .toList();
        var identifier = new UniqueSequenceIdentifier(4);
        return identifier.findUniqueSequenceIndex(sequence);
    }
}
