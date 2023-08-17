package com.github.chkypros.aoc2021.day16.evaluator;

import com.github.chkypros.aoc2021.day16.packet.BitsPacket;

import java.util.List;

public class GreaterOperatorEvaluator implements OperatorEvaluator {

    @Override
    public long evaluate(List<BitsPacket> subPackets) {
        return (subPackets.get(0).getValue() > subPackets.get(1).getValue()) ? 1L : 0L;
    }
}
