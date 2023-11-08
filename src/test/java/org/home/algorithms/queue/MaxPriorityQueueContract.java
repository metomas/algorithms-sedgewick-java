package org.home.algorithms.queue;

import org.home.algorithms.util.Testable;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.*;

public interface MaxPriorityQueueContract<E extends Comparable<E>,
        T extends MaxPriorityQueue<E>, U extends List<E>> extends Testable<T> {

    U createTestItems();

    @Test
    default void contract() {
        T queue = createInstance();
        U items = createTestItems();

        assertTrue(queue.isEmpty());
        assertNull(queue.max());

        items.forEach(item -> {
            queue.insert(item);
            assertFalse(queue.isEmpty());
        });

        items.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(item -> {
                    assertEquals(item, queue.max());
                    assertEquals(item, queue.deleteMax());
                });

        assertTrue(queue.isEmpty());
        assertNull(queue.max());
    }

    @Test
    default void deleteMaxUnderflow() {
        assertThatException()
                .isThrownBy(createInstance()::deleteMax)
                .isInstanceOf(NoSuchElementException.class);
    }

}
