package com.github.chkypros.aoc2022.day5;

import java.util.regex.Pattern;

public record CargoRearrangementStep(int cratesToMove, int sourceStack, int targetStack) {
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("move (\\d+) from (\\d) to (\\d)");

    public static CargoRearrangementStep from(String description) {
        final var matcher = DESCRIPTION_PATTERN.matcher(description);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Description: " + description + " doesn't follow pattern: " + DESCRIPTION_PATTERN.pattern());
        }

        return new CargoRearrangementStep(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3))
        );
    }
}
