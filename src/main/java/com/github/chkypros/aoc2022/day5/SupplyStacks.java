package com.github.chkypros.aoc2022.day5;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class SupplyStacks extends AbstractSolution {
    @Override
    public Object solvePartOne(Stream<String> stream) {
        return getTopCratesSequenceAfterRearrangement(stream, Cargo::performRearrangement);
    }

    @Override
    public Object solvePartTwo(Stream<String> stream) {
        return getTopCratesSequenceAfterRearrangement(stream, Cargo::performMultiRearrangement);
    }

    private static String getTopCratesSequenceAfterRearrangement(Stream<String> stream, BiConsumer<Cargo, CargoRearrangementStep> rearrangementStepBiConsumer) {
        final var inputLines = stream.collect(Collectors.toList());
        final var separatorLineIndex = IntStream.range(0, inputLines.size())
                .filter(i -> inputLines.get(i).trim().equals(""))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No empty separator line found!"));

        var cargoInitLines = inputLines.subList(0, separatorLineIndex);
        final var operationLines = inputLines.subList(separatorLineIndex + 1, inputLines.size());

        var cargo = new Cargo(cargoInitLines);

        operationLines.stream()
                .map(CargoRearrangementStep::from)
                .forEach(step -> rearrangementStepBiConsumer.accept(cargo, step));

        return cargo.getTopCratesSequence();
    }
}
