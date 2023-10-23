package org.home.algorithms.stack;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StackTest {

    @Test
    void isEmpty() {
        TestStack stack = new TestStack();

        assertFalse(stack.isEmpty());
        assertThat(stack.isEmpty()).isFalse();
    }

    private static class TestStack implements Stack<String> {

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void push(String item) {

        }

        @Override
        public String pop() throws IllegalStateException {
            return null;
        }

        @Override
        public Iterator<String> iterator() {
            return null;
        }
    }
}