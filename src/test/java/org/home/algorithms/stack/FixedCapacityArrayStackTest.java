package org.home.algorithms.stack;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatException;

class FixedCapacityArrayStackTest implements StackContract<String, Stack<String>, List<String>> {

    @Override
    public Stack<String> createInstance() {
        return new FixedCapacityArrayStack<>(createTestItems().size());
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

    @Test
    void overflow() {
        Stack<String> stack = new FixedCapacityArrayStack<>(0);

        assertThatException()
                .isThrownBy(() -> stack.push("a"))
                .isExactlyInstanceOf(StackOverflowException.class);
    }
}
