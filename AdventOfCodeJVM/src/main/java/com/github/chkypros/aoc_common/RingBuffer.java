package com.github.chkypros.aoc_common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:kypros.chrysanthou@britebill.com">Kypros Chrysanthou</a>
 */
public class RingBuffer<T> {
    private final List<T> buffer;
    private final int size;
    private int head = 0;
    private int length = 0;

    public RingBuffer(int size) {
        this.size = size;

        buffer = new ArrayList<>(size);
        IntStream.range(0, size)
                .forEach(i -> buffer.add(null));
    }

    public int length() {
        return length;
    }

    public void push(T element) {
        buffer.set((head + length) % size, element);
        if (length < size) {
            length++;
        } else {
            head = (head + 1) % size;
        }
    }

    public T peek() {
        return buffer.get(head);
    }

    public T pop() {
        var element = buffer.get(head);
        head = (head + 1) % size;
        length--;
        return element;
    }

    public List<T> getElements() {
        return new ArrayList<>(buffer);
    }
}
