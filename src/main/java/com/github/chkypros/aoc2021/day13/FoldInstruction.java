package com.github.chkypros.aoc2021.day13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FoldInstruction {
    private static final Pattern PATTERN = Pattern.compile("fold along (?<axis>[xy])=(?<index>[0-9]+)");

    private final String axis;
    private final int index;

    private FoldInstruction(String axis, int index) {
        this.axis = axis;
        this.index = index;
    }

    public String getAxis() {
        return axis;
    }

    public int getIndex() {
        return index;
    }

    public static FoldInstruction of(String instruction) {
        final Matcher matcher = PATTERN.matcher(instruction);
        matcher.find();
        return new FoldInstruction(matcher.group("axis"), Integer.parseInt(matcher.group("index")));
    }
}
