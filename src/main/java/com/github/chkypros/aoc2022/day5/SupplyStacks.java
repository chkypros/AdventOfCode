package com.github.chkypros.aoc2022.day5;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class SupplyStacks extends AbstractSolution {
    @Override
    public Object solvePartOne(Stream<String> stream) {
        var cargoInitLines = stream.collect(Collectors.toList());
        var cargo = new Cargo(cargoInitLines);
        return "AB";
    }
}
