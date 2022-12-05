package com.github.chkypros.aoc2020.day5;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class BinaryBoarding extends AbstractSolution {
    public static void main(String[] args) throws Exception {
        new BinaryBoarding().solve();
    }

    @Override
    public Long solvePartOne(Stream<String> stream) {
        return stream.map(s -> s.replaceAll("F|L","0").replaceAll("B|R", "1"))
            .mapToLong(s -> Integer.parseInt(s, 2))
            .max()
            .getAsLong();
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        final List<Integer> integerList = stream.map(s -> s.replaceAll("F|L", "0").replaceAll("B|R", "1"))
            .map(s -> Integer.parseInt(s, 2))
            .sorted()
            .collect(Collectors.toList());

        for (int i = 0; i < integerList.size() - 1; i++) {
            if (integerList.get(i + 1) > integerList.get(i) + 1) {
                return integerList.get(i) + 1L;
            }
        }
        return -1L;
    }
}
