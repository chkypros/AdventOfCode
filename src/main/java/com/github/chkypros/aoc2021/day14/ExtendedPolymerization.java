package com.github.chkypros.aoc2021.day14;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExtendedPolymerization extends AbstractSolution {

    private final Map<String, Map<Character, Long>> countsCache = new HashMap<>();

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return solveExtendedPolymerization(stream, 10);
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        return solveExtendedPolymerization(stream, 40);
    }

    private long solveExtendedPolymerization(Stream<String> stream, int steps) {
        final Map<Character, Long> characterCount = applyPolymerPairInsertions(stream, steps);

        final long[] counts = characterCount.values().stream()
                .mapToLong(Long::longValue)
                .sorted()
                .toArray();

        return counts[counts.length - 1] - counts[0];
    }

    private Map<Character, Long> applyPolymerPairInsertions(Stream<String> stream, int steps) {
        final List<String> input = stream.collect(Collectors.toList());
        String polymer = input.get(0);
        final Map<String, String> pairInsertions = IntStream.range(2, input.size())
                .mapToObj(input::get)
                .map(pairInsertion -> pairInsertion.split(" -> "))
                .collect(Collectors.toMap(
                        pairInsertion -> pairInsertion[0],
                        pairInsertion -> pairInsertion[1]
                ));

        Map<Character, Long> characterTotalCount = new HashMap<>();
        characterTotalCount.put(polymer.charAt(polymer.length() - 1), 1L);
        for (int i = 0; i < polymer.length() - 1; i++) {
            Map<Character, Long> characterCount = applyPairInsertions(polymer.substring(i, i + 2), pairInsertions, steps);
            characterCount.forEach((character, count) ->
                    characterTotalCount.put(character, count + characterTotalCount.getOrDefault(character, 0L)));
        }

        return characterTotalCount;
    }

    private Map<Character, Long> applyPairInsertions(String pair, Map<String, String> pairInsertions, int steps) {
        final String cacheKey = pair + steps;
        if (countsCache.containsKey(cacheKey)) {
            return countsCache.get(cacheKey);
        }

        if (0 == steps || !pairInsertions.containsKey(pair)) {
            Map<Character, Long> map = new HashMap<>();
            map.put(pair.charAt(0), 1L);
            return map;
        }

        final String insert = pairInsertions.get(pair);
        final Map<Character, Long> firstMap = applyPairInsertions(pair.charAt(0) + insert, pairInsertions, steps - 1);
        final Map<Character, Long> secondMap = applyPairInsertions(insert + pair.charAt(1), pairInsertions, steps - 1);

        Map<Character, Long> map = new HashMap<>(firstMap);
        secondMap.forEach((character, count) -> map.merge(
                character,
                count,
                Long::sum
        ));

        countsCache.put(cacheKey, map);
        return map;
    }
}
