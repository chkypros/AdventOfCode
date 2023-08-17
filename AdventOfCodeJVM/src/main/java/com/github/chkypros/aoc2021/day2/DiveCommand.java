package com.github.chkypros.aoc2021.day2;

class DiveCommand {
    private DiveAction action;
    private int value;

    public DiveCommand(DiveAction action, int value) {
        this.action = action;
        this.value = value;
    }

    public DiveAction getAction() {
        return action;
    }

    public int getValue() {
        return value;
    }

    public static DiveCommand of(final String commandStr) {
        return new DiveCommand(getAction(commandStr), getValue(commandStr));
    }

    private static DiveAction getAction(final String command) {
        return DiveAction.of(command.split(" ")[0]);
    }

    private static int getValue(final String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }

}
