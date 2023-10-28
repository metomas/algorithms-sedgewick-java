package org.home.algorithms.stack;

import java.util.Iterator;

public class FixedCapacityArrayStack<E> implements Stack<E> {

    private final E[] stack;
    private int n = 0;

    @SuppressWarnings("unchecked")
    public FixedCapacityArrayStack(int capacity) {
        this.stack = (E[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void push(E item) {
        if (n == stack.length) {
            throw new IllegalStateException("Full stack");
        }

        stack[n++] = item;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty stack");
        }

        E item = stack[--n];
        stack[n] = null; // avoid loitering to allow GC reclaim memory
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<E> {

        private int i;

        public ReverseArrayIterator() {
            i = n;
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            if(!hasNext()) throw new NoSuchElementException();
            return stack[--i];
        }
    }
}
