package com.github.chkypros.aoc2021.day16.evaluator;

import com.github.chkypros.aoc2021.day16.packet.BitsPacket;

import java.util.List;

public class MinOperatorEvaluator implements OperatorEvaluator {

    @Override
    public long evaluate(List<BitsPacket> subPackets) {
        return subPackets.stream()
            .mapToLong(BitsPacket::getValue)
            .min()
            .getAsLong();
    }
}
