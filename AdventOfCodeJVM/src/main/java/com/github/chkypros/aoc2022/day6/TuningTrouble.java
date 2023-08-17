package com.github.chkypros.aoc2022.day6;

import com.github.chkypros.aoc_template.AbstractSolution;
import com.github.chkypros.aoc_template.RingBuffer;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class TuningTrouble extends AbstractSolution {
    @Override
    public Object solvePartOne(Stream<String> stream) {
        return findMarker(stream, 4);
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        return findMarker(stream, 14);
    }

    private int findMarker(Stream<String> stream, int lookbackDepth) {
        var sequence = stream.flatMapToInt(String::chars)
            .mapToObj(c -> (char) c)
            .toList();

        var buffer = new RingBuffer<Character>(lookbackDepth);
        var index = 0;

        for (Character character : sequence) {
            index++;
            buffer.push(character);
            if (countUniqueElements(buffer) == lookbackDepth) {
                break;
            }
        }

        return index;
    }

    private long countUniqueElements(RingBuffer<Character> buffer) {
        return buffer.getElements().stream()
                .filter(Objects::nonNull)
                .distinct()
                .count();
    }
}
