package com.github.chkypros.aoc2021.day16.packet;

import com.github.chkypros.aoc2021.day16.BitsPacketType;

public abstract class BitsPacket {
    protected final int packetSize;
    protected final int version;
    protected final BitsPacketType type;

    public BitsPacket(int packetSize, int version, BitsPacketType type) {
        this.packetSize = packetSize;
        this.version = version;
        this.type = type;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public int getVersion() {
        return version;
    }

    public BitsPacketType getType() {
        return type;
    }

    public abstract long getValue();
}
