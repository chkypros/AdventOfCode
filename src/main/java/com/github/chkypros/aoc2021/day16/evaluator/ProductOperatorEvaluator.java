package com.github.chkypros.aoc2021.day16.evaluator;

import com.github.chkypros.aoc2021.day16.packet.BitsPacket;

import java.util.List;

public class ProductOperatorEvaluator implements OperatorEvaluator {

    @Override
    public long evaluate(List<BitsPacket> subPackets) {
        return subPackets.stream()
            .mapToLong(BitsPacket::getValue)
            .reduce(1, (v1, v2) -> v1 * v2);
    }
}
