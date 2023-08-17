package com.github.chkypros.aoc2021.day16.packet;

import com.github.chkypros.aoc2021.day16.BitsPacketType;

public class LiteralBitsPacket extends BitsPacket {
    private final long value;

    public LiteralBitsPacket(int packetSize, int version, BitsPacketType type, long value) {
        super(packetSize, version, type);
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
