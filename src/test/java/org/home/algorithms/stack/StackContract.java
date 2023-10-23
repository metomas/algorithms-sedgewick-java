package org.home.algorithms.stack;

import org.home.algorithms.util.Testable;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

public interface StackContract<E, T extends Stack<E>, U extends List<E>> extends Testable<T> {

    U createTestItems();

    @Test
    default void testStack() {
        T stack = createInstance();
        U items = createTestItems();

        assertThat(stack.isEmpty()).isTrue();

        items.forEach(item -> {
            stack.push(item);
            assertThat(stack.isEmpty()).isFalse();
        });

        ListIterator<E> it = items.listIterator(items.size());
        while (it.hasPrevious()) {
            assertThat(stack.pop()).isEqualTo(it.previous());
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    default void testUnderflow() {
        assertThatException()
                .isThrownBy(createInstance()::pop)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    default void testIterator() {
        T stack = createInstance();
        U items = createTestItems();

        items.forEach(stack::push);

        Iterator<E> stackIterator = stack.iterator();

        ListIterator<E> itemsIterator = items.listIterator(items.size());
        while (itemsIterator.hasPrevious()) {
            assertThat(stackIterator.hasNext()).isTrue();
            assertThat(stackIterator.next()).isEqualTo(itemsIterator.previous());
        }

        assertThat(stackIterator.hasNext()).isFalse();
    }

}
