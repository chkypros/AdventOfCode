package com.github.chkypros.aoc2021.day16.parser;

import com.github.chkypros.aoc2021.day16.BitsPacketType;
import com.github.chkypros.aoc2021.day16.packet.BitsPacket;
import com.github.chkypros.aoc2021.day16.packet.OperatorBitsPacket;

import java.util.ArrayList;
import java.util.List;

public class OperatorBitsPacketParser {

    public OperatorBitsPacketParser() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static BitsPacket parse(String input, int startIndex, int version, BitsPacketType type) {
        List<BitsPacket> subPackets;
        int packetSize = 7;

        final int pastHeaderIndex = startIndex + 7;
        if ('0' == input.charAt(startIndex + 6)) {
            final int lengthBits = 15;
            final int startBodyIndex = pastHeaderIndex + lengthBits;
            int bitLength = Integer.parseInt(input.substring(pastHeaderIndex, startBodyIndex), 2);
            subPackets = getSubPacketsInRange(input.substring(startBodyIndex, startBodyIndex + bitLength));
            packetSize += lengthBits + bitLength;
        } else {
            final int lengthBits = 11;
            final int startBodyIndex = pastHeaderIndex + lengthBits;
            int subPacketsCount = Integer.parseInt(input.substring(pastHeaderIndex, startBodyIndex), 2);
            subPackets = getSubpacketsByCount(input.substring(startBodyIndex), subPacketsCount);
            packetSize += lengthBits + subPackets.stream()
                    .mapToInt(BitsPacket::getPacketSize)
                    .sum();
        }

        return new OperatorBitsPacket(packetSize, version, type, subPackets);
    }

    private static List<BitsPacket> getSubPacketsInRange(String substring) {
        final List<BitsPacket> subPackets = new ArrayList<>();

        int index = 0;
        while (index < substring.length()) {
            final BitsPacket packet = BitsPacketParser.parse(substring, index);
            subPackets.add(packet);
            index += packet.getPacketSize();
        }

        return subPackets;
    }

    private static List<BitsPacket> getSubpacketsByCount(String substring, int subPacketsCount) {
        final List<BitsPacket> subPackets = new ArrayList<>();

        int index = 0;
        int subPacketsFound = 0;
        while (subPacketsFound < subPacketsCount) {
            final BitsPacket packet = BitsPacketParser.parse(substring, index);
            subPackets.add(packet);
            index += packet.getPacketSize();
            subPacketsFound++;
        }

        return subPackets;
    }
}
