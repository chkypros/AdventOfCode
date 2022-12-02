package com.github.chkypros.aoc2021.day16;

import com.github.chkypros.aoc2021.day16.packet.BitsPacket;
import com.github.chkypros.aoc2021.day16.packet.OperatorBitsPacket;
import com.github.chkypros.aoc2021.day16.parser.BitsPacketParser;
import com.github.chkypros.aoc_template.AbstractSolution;
import com.github.chkypros.aoc_template.Utils;

import java.util.stream.Stream;

public class PacketDecoder extends AbstractSolution {
    @Override
    public Long solvePartOne(Stream<String> stream) {
        final BitsPacket packet = getBitsPacket(stream);

        return getTotalVersionsSum(packet);
    }

    private BitsPacket getBitsPacket(Stream<String> stream) {
        final String inputHexadecimal = stream.reduce("", (s, s2) -> s + s2);
        final String inputBinary = Utils.hexToBinary(inputHexadecimal);
        return BitsPacketParser.parse(inputBinary, 0);
    }

    private long getTotalVersionsSum(BitsPacket packet) {
        long versionsSum = packet.getVersion();
        if (packet instanceof OperatorBitsPacket) {
            final OperatorBitsPacket operatorBitsPacket = (OperatorBitsPacket) packet;
            versionsSum += operatorBitsPacket.getSubPackets().stream()
                .mapToLong(this::getTotalVersionsSum)
                .sum();
        }

        return versionsSum;
    }

    @Override
    public Long solvePartTwo(Stream<String> stream) {
        final BitsPacket packet = getBitsPacket(stream);

        return packet.getValue();
    }
}
