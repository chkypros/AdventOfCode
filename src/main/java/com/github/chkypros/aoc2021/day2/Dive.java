package com.github.chkypros.aoc2021.day2;

import com.github.chkypros.aoc_template.AbstractSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingLong;

public class Dive extends AbstractSolution {

    private final Map<DiveAction.Direction, Long> initialPosition = new HashMap<>();

    public Dive(int initialHorizontalPosition, int initialDepth) {
        this.initialPosition.put(DiveAction.Direction.HORIZONTAL, (long) initialHorizontalPosition);
        this.initialPosition.put(DiveAction.Direction.VERTICAL, (long) initialDepth);
    }

    @Override
    public Long solvePartOne(Stream<String> stream) {

        Map<DiveAction, Long> sumsByAction = stream
                .map(DiveCommand::of)
                .collect(Collectors.groupingBy(DiveCommand::getAction, summingLong(DiveCommand::getValue)));

        HashMap<DiveAction.Direction, Long> position = new HashMap<>(initialPosition);
        sumsByAction.forEach((action, sum) ->
                position.computeIfPresent(
                        action.getDirection(),
                        (direction, previousPosition) -> previousPosition + sum * action.getMultiplier())
        );

        return position.get(DiveAction.Direction.HORIZONTAL) * position.get(DiveAction.Direction.VERTICAL);
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {

        List<DiveCommand> commands = stream.map(DiveCommand::of).collect(Collectors.toList());

        int aim = 0;
        HashMap<DiveAction.Direction, Long> position = new HashMap<>(initialPosition);
        for (DiveCommand command : commands) {
            switch (command.getAction().getDirection()) {
                case HORIZONTAL:
                    long horizontalPosition = position.get(DiveAction.Direction.HORIZONTAL) + command.getValue();
                    long depth = position.get(DiveAction.Direction.VERTICAL) + (long) aim * command.getValue();

                    position.put(DiveAction.Direction.HORIZONTAL, horizontalPosition);
                    position.put(DiveAction.Direction.VERTICAL, depth);
                    break;
                case VERTICAL:
                    aim += command.getValue() * command.getAction().getMultiplier();
            }
        }

        return position.get(DiveAction.Direction.HORIZONTAL) * position.get(DiveAction.Direction.VERTICAL);
    }
}
