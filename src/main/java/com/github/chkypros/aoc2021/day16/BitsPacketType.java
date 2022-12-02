package com.github.chkypros.aoc2021.day16;

import java.util.Arrays;

public enum BitsPacketType {
    SUM,
    PRODUCT,
    MIN,
    MAX,
    LITERAL,
    GREATER,
    LESSER,
    EQUAL,
    ;

    public static BitsPacketType of(int typeId) {
        return Arrays.stream(BitsPacketType.values())
            .filter(bitsPacketType -> bitsPacketType.getId() == typeId)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public int getId() {
        return ordinal();
    }
}
