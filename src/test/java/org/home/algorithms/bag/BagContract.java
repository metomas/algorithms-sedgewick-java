package org.home.algorithms.bag;

import org.home.algorithms.util.Testable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.*;

public interface BagContract<E, T extends Bag<E>, U extends List<E>> extends Testable<T> {

    U createTestItems();

    @Test
    default void contract() {
        T bag = createInstance();
        U items = createTestItems();

        int n = 0;
        assertEquals(n, bag.size());

        for (E item : items) {
            bag.add(item);
            assertEquals(++n, bag.size());
        }
    }

    @Test
    default void iterator() {
        T bag = createInstance();
        U items = createTestItems();

        items.forEach(bag::add);

        List<E> bagItems = new ArrayList<>();
        Iterator<E> it = bag.iterator();
        items.forEach(item -> {
            assertTrue(it.hasNext());
            bagItems.add(it.next());
        });

        assertFalse(it.hasNext());
        assertThat(bagItems).containsExactlyInAnyOrderElementsOf(items);
        assertThatException()
                .isThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

}