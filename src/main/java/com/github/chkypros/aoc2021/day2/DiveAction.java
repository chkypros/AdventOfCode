package com.github.chkypros.aoc2021.day2;

public enum DiveAction {
    FORWARD(Direction.HORIZONTAL, 1),
    DOWN(Direction.VERTICAL, 1),
    UP(Direction.VERTICAL, -1)
    ;

    private final Direction direction;
    private final int multiplier;

    DiveAction(Direction direction, int multiplier) {
        this.direction = direction;
        this.multiplier = multiplier;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static DiveAction of(final String commandStr) {
        return valueOf(commandStr.toUpperCase());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public enum Direction { HORIZONTAL, VERTICAL }
}
