package org.home.algorithms.stack;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatException;

class LinkedListStackTest implements StackContract<String, Stack<String>, List<String>> {

    @Override
    public Stack<String> createInstance() {
        return new LinkedListStack<>();
    }

    @Override
    public List<String> createTestItems() {
        return List.of("a", "b", "c");
    }

    @Test
    public void underflow() {
        assertThatException()
                .isThrownBy(createInstance()::pop)
                .isInstanceOf(NoSuchElementException.class)
                .withMessage("Stack underflow");
    }
}