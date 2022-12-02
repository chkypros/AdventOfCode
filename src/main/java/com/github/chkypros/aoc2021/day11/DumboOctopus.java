package com.github.chkypros.aoc2021.day11;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DumboOctopus extends AbstractSolution {

    @Override
    public Long solvePartOne(Stream<String> stream) {
        final List<String> energyLevelsList = stream.collect(Collectors.toList());
        final int rows = energyLevelsList.size();
        final int columns = energyLevelsList.get(0).length();
        final int[][] energyLevels = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                energyLevels[i][j] = Integer.parseInt(energyLevelsList.get(i).substring(j, j + 1));
            }
        }

        final DumboOctopusEnergyEvaluator dumboOctopusEnergyEvaluator = new DumboOctopusEnergyEvaluator(energyLevels);
        dumboOctopusEnergyEvaluator.evaluateSteps(100);
        return dumboOctopusEnergyEvaluator.getTotalFlashes();
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        final List<String> energyLevelsList = stream.collect(Collectors.toList());
        final int rows = energyLevelsList.size();
        final int columns = energyLevelsList.get(0).length();
        final int[][] energyLevels = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                energyLevels[i][j] = Integer.parseInt(energyLevelsList.get(i).substring(j, j + 1));
            }
        }

        final DumboOctopusEnergyEvaluator dumboOctopusEnergyEvaluator = new DumboOctopusEnergyEvaluator(energyLevels);
        return dumboOctopusEnergyEvaluator.findNextSynchronizedStep();
    }
}
