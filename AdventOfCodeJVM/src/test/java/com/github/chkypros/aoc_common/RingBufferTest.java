package com.github.chkypros.aoc_common;

import com.github.chkypros.aoc_common.RingBuffer;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class RingBufferTest {
    private static final int DEFAULT_SIZE = 3;
    private RingBuffer<Character> ringBuffer;

    @Before
    public void setUp() {
        ringBuffer = new RingBuffer<>(DEFAULT_SIZE);
    }

    @Test
    public void addElement() {
        ringBuffer.push('a');
        assertEquals(1, ringBuffer.length());
    }

    @Test
    public void removeElement() {
        ringBuffer.push('a');
        ringBuffer.push('b');
        var element = ringBuffer.pop();
        assertEquals(1, ringBuffer.length());
        assertEquals(Character.valueOf('b'), ringBuffer.peek());
    }

    @Test
    public void overflowBuffer() {
        IntStream.range(0, DEFAULT_SIZE + 1)
                .forEach(i -> ringBuffer.push(Character.forDigit(i, 10)));

        assertEquals(DEFAULT_SIZE, ringBuffer.length());
    }

    @Test
    public void getElements() {
        IntStream.range(0, DEFAULT_SIZE + 1)
                .forEach(i -> ringBuffer.push(Character.forDigit(i, 10)));

        var elements = ringBuffer.getElements();

        IntStream.range(1, DEFAULT_SIZE + 1)
                .forEach(i -> assertEquals(Character.forDigit(i, 10), elements.get(i % DEFAULT_SIZE).charValue()));
    }
}
