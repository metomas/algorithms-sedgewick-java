package org.home.algorithms.queue;

import org.home.algorithms.util.Testable;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public interface QueueContract<E, T extends Queue<E>, U extends List<E>> extends Testable<T> {

    U createTestItems();

    @Test
    default void testQueue() {
        T queue = createInstance();
        U items = createTestItems();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        int i = 0;
        for (E item : items) {
            queue.enqueue(item);

            assertFalse(queue.isEmpty());
            assertEquals(++i, queue.size());
        }

        int j = items.size();
        for (E item : items) {
            assertEquals(item, queue.dequeue());
            assertEquals(--j, queue.size());
        }

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    default void testIterator() {
        T queue = createInstance();
        U items = createTestItems();

        items.forEach(queue::enqueue);

        Iterator<E> it = queue.iterator();
        items.forEach(item -> {
            assertTrue(it.hasNext());
            assertEquals(item, it.next());
        });

        assertFalse(it.hasNext());
    }
}
