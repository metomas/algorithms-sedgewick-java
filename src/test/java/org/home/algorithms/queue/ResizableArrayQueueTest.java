package org.home.algorithms.queue;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void resettingHeadAndTailAfterResizing() {
        var queue = createInstance();
        var items = List.of("a", "b", "c");
        var expected = new LinkedList<String>();

        // enqueue all items
        for (String item : items) {
            queue.enqueue(item);
            expected.add(item);
        }

        // dequeue a single item
        assertEquals(items.get(0), queue.dequeue());
        expected.removeFirst();

        // enqueue another two items
        for (String item : List.of("d", "e")) {
            queue.enqueue(item);
            expected.add(item);
        }

        // dequeue all items
        for (String item : expected) {
            assertEquals(item, queue.dequeue());
        }
    }

}