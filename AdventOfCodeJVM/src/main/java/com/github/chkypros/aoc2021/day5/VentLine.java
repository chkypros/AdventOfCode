package com.github.chkypros.aoc2021.day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentLine {
    private static final Pattern PATTERN = Pattern.compile("(?<x1>\\d+),(?<y1>\\d+) -> (?<x2>\\d+),(?<y2>\\d+)");

    private final Point start;
    private final Point end;

    private final boolean isDiagonal;

    public VentLine(Point start, Point end) {
        this.start = start;
        this.end = end;

        isDiagonal = start.x != end.x && start.y != end.y;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean isDiagonal() {
        return isDiagonal;
    }

    public static VentLine of(String string) {
        final Matcher matcher = PATTERN.matcher(string);
        if (!matcher.find()) {
            return null;
        }

        final Point start = Point.of(getIntGroup(matcher, "x1"), getIntGroup(matcher, "y1"));
        final Point end = Point.of(getIntGroup(matcher, "x2"), getIntGroup(matcher, "y2"));

        return new VentLine(start, end);
    }

    private static int getIntGroup(Matcher matcher, String group) {
        return Integer.parseInt(matcher.group(group));
    }
}
