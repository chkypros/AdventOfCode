package com.github.chkypros.aoc2021.day16.evaluator;

import com.github.chkypros.aoc2021.day16.packet.BitsPacket;

import java.util.List;

public interface OperatorEvaluator {
    long evaluate(List<BitsPacket> subPackets);
}
