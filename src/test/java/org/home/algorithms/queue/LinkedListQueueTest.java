package org.home.algorithms.queue;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatException;

class LinkedListQueueTest implements QueueContract<String, Queue<String>, List<String>> {

    @Override
    public Queue<String> createInstance() {
        return new LinkedListQueue<>();
    }

    @Override
    public List<String> createTestItems() {
        return List.of("a", "b", "c");
    }

    @Test
    void underflow() {
        assertThatException()
                .isThrownBy(createInstance()::dequeue)
                .isInstanceOf(NoSuchElementException.class)
                .withMessage("Queue underflow");
    }

}