package com.github.chkypros.aoc2021.day16.parser;

import com.github.chkypros.aoc2021.day16.BitsPacketType;
import com.github.chkypros.aoc2021.day16.packet.BitsPacket;

public class BitsPacketParser {
    private BitsPacketParser() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static BitsPacket parse(String input, int startIndex) {
        final int version = Integer.parseInt(input.substring(startIndex, startIndex + 3), 2);
        final int typeId = Integer.parseInt(input.substring(startIndex + 3, startIndex + 6), 2);

        BitsPacketType type = BitsPacketType.of(typeId);
        return  (type.getId() == 4)
            ? LiteralBitsPacketParser.parse(input, startIndex, version)
            : OperatorBitsPacketParser.parse(input, startIndex, version, type);
    }

}
