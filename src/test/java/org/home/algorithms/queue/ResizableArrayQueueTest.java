package org.home.algorithms.queue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatException;

class ResizableArrayQueueTest implements QueueContract<String, Queue<String>, List<String>> {

    @Override
    public Queue<String> createInstance() {
        return new ResizableArrayQueue<>();
    }

    @Override
    public List<String> createTestItems() {
        return List.of("a", "b", "c");
    }

    @Test
    void testUnderflow() {
        assertThatException()
                .isThrownBy(createInstance()::dequeue)
                .isInstanceOf(IllegalStateException.class)
                .withMessage("Empty queue");
    }

}