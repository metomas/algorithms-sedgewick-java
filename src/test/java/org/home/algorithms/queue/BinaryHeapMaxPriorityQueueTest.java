package org.home.algorithms.queue;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatException;

class BinaryHeapMaxPriorityQueueTest
        implements MaxPriorityQueueContract<String, MaxPriorityQueue<String>, List<String>> {

    @Override
    public MaxPriorityQueue<String> createInstance() {
        return new BinaryHeapMaxPriorityQueue<>();
    }

    @Override
    public List<String> createTestItems() {
        return List.of("a", "d", "c", "b");
    }

    @Test
    public void maxUnderflow() {
        assertThatException()
                .isThrownBy(createInstance()::max)
                .isInstanceOf(NoSuchElementException.class)
                .withMessage("Queue underflow");
    }

    @Test
    public void deleteMaxUnderflow() {
        assertThatException()
                .isThrownBy(createInstance()::deleteMax)
                .isInstanceOf(NoSuchElementException.class)
                .withMessage("Queue underflow");
    }

}