package com.github.chkypros.aoc2021.day16.parser;

import com.github.chkypros.aoc2021.day16.BitsPacketType;
import com.github.chkypros.aoc2021.day16.packet.BitsPacket;
import com.github.chkypros.aoc2021.day16.packet.LiteralBitsPacket;

class LiteralBitsPacketParser {
    private LiteralBitsPacketParser() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static BitsPacket parse(String input, int startIndex, int version) {
        int index = startIndex + 6;
        final StringBuilder sb = new StringBuilder();

        char controlBit;
        do {
            controlBit = input.charAt(index);
            final String bitGroup = input.substring(index + 1, index + 5);
            sb.append(bitGroup);

            index +=5;
        } while ('1' == controlBit);

        long value = Long.parseLong(sb.toString(), 2);
        return new LiteralBitsPacket(index - startIndex, version, BitsPacketType.LITERAL, value);
    }
}
