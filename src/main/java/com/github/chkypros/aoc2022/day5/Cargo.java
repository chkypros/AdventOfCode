package com.github.chkypros.aoc2022.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class Cargo {
    private static final Pattern STACK_LABEL_PATTERN = Pattern.compile("[1-9]");

    private final List<Stack<Character>> stacks;

    public Cargo(List<String> initLines) {
        final var stackLabelsLine = initLines.get(initLines.size() - 1);
        final var maxLabel = stackLabelsLine.substring(stackLabelsLine.length() - 2, stackLabelsLine.length() - 1);
        final var numberOfStacks = Integer.parseInt(maxLabel);

        stacks = new ArrayList<>(numberOfStacks);
        final var matcher = STACK_LABEL_PATTERN.matcher(stackLabelsLine);
        while (matcher.find()) {
            final var labelIndex = matcher.start();
            final var stack = new Stack<Character>();
            for (int i = initLines.size() - 2; i >= 0; i--) {
                if (initLines.get(i).charAt(labelIndex) == ' ') {
                    break;
                }

                stack.push(initLines.get(i).charAt(labelIndex));
            }

            stacks.add(stack);
        }
    }

    public String getTopCratesSequence() {
        return stacks.stream()
                .map(Stack::peek)
                .map(String::valueOf)
                .reduce(String::concat)
                .orElse("");
    }

    public void performRearrangement(CargoRearrangementStep step) {
        IntStream.range(0, step.cratesToMove())
                .forEach(i -> moveCrates(step.sourceStack(), step.targetStack(), 1));
    }

    private void moveCrates(int sourceStack, int targetStack, int cratesToMove) {
        final var sourceStackIndex = sourceStack - 1;
        final var targetStackIndex = targetStack - 1;

        final var tempStack = new Stack<Character>();
        IntStream.range(0, cratesToMove)
                .forEach(i -> moveCrate(stacks.get(sourceStackIndex), tempStack));
        IntStream.range(0, cratesToMove)
                .forEach(i -> moveCrate(tempStack, stacks.get(targetStackIndex)));
    }

    private static void moveCrate(Stack<Character> source, Stack<Character> target) {
        target.push(source.pop());
    }

    public void performMultiRearrangement(CargoRearrangementStep step) {
        moveCrates(step.sourceStack(), step.targetStack(), step.cratesToMove());
    }
}
