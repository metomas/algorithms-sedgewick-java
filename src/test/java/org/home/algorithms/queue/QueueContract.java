package org.home.algorithms.queue;

import org.home.algorithms.util.Testable;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.*;

public interface QueueContract<E, T extends Queue<E>, U extends List<E>> extends Testable<T> {

    U createTestItems();

    @Test
    default void contract() {
        T queue = createInstance();
        U items = createTestItems();
        int expectedSize = 0;

        assertTrue(queue.isEmpty());
        assertEquals(expectedSize, queue.size());

        for (E item : items) {
            queue.enqueue(item);

            assertFalse(queue.isEmpty());
            assertEquals(++expectedSize, queue.size());
        }

        for (E item : items) {
            assertEquals(item, queue.dequeue());
            assertEquals(--expectedSize, queue.size());
        }

        assertTrue(queue.isEmpty());
        assertEquals(expectedSize, queue.size());
    }

    @Test
    default void iterator() {
        T queue = createInstance();
        U items = createTestItems();

        items.forEach(queue::enqueue);

        Iterator<E> it = queue.iterator();
        items.forEach(item -> {
            assertTrue(it.hasNext());
            assertEquals(item, it.next());
        });

        assertFalse(it.hasNext());
        assertThatException()
                .isThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}
