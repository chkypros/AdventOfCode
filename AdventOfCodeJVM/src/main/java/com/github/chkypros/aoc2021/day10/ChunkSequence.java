package com.github.chkypros.aoc2021.day10;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class ChunkSequence {
    private static final List<Character> OPENING_BRACES = Arrays.asList('(', '[', '{', '<');
    private static final List<Character> CLOSING_BRACES = Arrays.asList(')', ']', '}', '>');

    private final String string;
    private final Deque<Character> deque = new ArrayDeque<>();

    private int illegalCharacterIndex = -1;

    public ChunkSequence(String string) {
        this.string = string;

        findNextIllegalCharacter();
    }

    public static ChunkSequence of(String string) {
        return new ChunkSequence(string);
    }

    public boolean findNextIllegalCharacter() {
        final int start = -1 != illegalCharacterIndex ? illegalCharacterIndex : 0;
        for (int i = start; i < string.length(); i++) {
            final char brace = string.charAt(i);
            if (OPENING_BRACES.contains(brace)) {
                deque.push(brace);
            } else {
                final int braceIndex = OPENING_BRACES.indexOf(deque.peek());
                if (CLOSING_BRACES.get(braceIndex) == brace) {
                    deque.pop();
                } else {
                    illegalCharacterIndex = i;
                    return true;
                }
            }
        }

        return false;
    }

    public char getIllegalBrace() {
        return string.charAt(illegalCharacterIndex);
    }

    public boolean isCorrupted() {
        return illegalCharacterIndex != -1;
    }

    public boolean isIncomplete() {
        return !isCorrupted() && deque.size() != 0;
    }

    public String getMissingClosingBraces() {
        final StringBuilder sb = new StringBuilder();

        while (!deque.isEmpty()) {
            final Character openingBrace = deque.pop();
            final int braceIndex = OPENING_BRACES.indexOf(openingBrace);
            sb.append(CLOSING_BRACES.get(braceIndex));
        }

        return sb.toString();
    }
}
