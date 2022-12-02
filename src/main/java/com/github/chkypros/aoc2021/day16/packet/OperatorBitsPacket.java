package com.github.chkypros.aoc2021.day16.packet;

import com.github.chkypros.aoc2021.day16.BitsPacketType;
import com.github.chkypros.aoc2021.day16.evaluator.OperatorEvaluatorFactory;

import java.util.List;

public class OperatorBitsPacket extends BitsPacket {
    private final List<BitsPacket> subPackets;

    public OperatorBitsPacket(int packetSize, int version, BitsPacketType type, List<BitsPacket> subPackets) {
        super(packetSize, version, type);

        this.subPackets = subPackets;
    }

    public List<BitsPacket> getSubPackets() {
        return subPackets;
    }

    @Override
    public long getValue() {
        return OperatorEvaluatorFactory.get(type)
            .evaluate(subPackets);
    }
}
