package org.home.algorithms.stack;

import java.util.Iterator;

/**
 * Memory usage
 * <p>
 * Uses between ~8N and ~32N bytes to represent a stack with N items
 * <ul>
 *     <li>~8N when full</li>
 *     <li>~32N when one-quarter full</li>
 * </ul>
 * <p>
 * Remark: Analysis includes memory for the stack (but not the items themselves)
 * <p>
 * Time complexity
 * <table>
 *     <tr><th>operation</th><th>best</th><th>worst</th><th>amortized</th></tr>
 *     <tr><td>construct</td><td>1</td><td>1</td><td>1</td></tr>
 *     <tr><td>push</td><td>1</td><td>N</td><td>1</td></tr>
 *     <tr><td>pop</td><td>1</td><td>N</td><td>1</td></tr>
 *     <tr><td>size</td><td>1</td><td>1</td><td>1</td></tr>
 * </table>
 * Amortized analysis - average running time per operation over a worst-case sequence of operations
 * <p>
 * Pros:
 * <ul>
 *     <li>every operation takes constant amortized time</li>
 *     <li>less wasted space in comparison with linked-list implementation</li>
 * </ul>
 */
public class ResizableArrayStack<E> implements Stack<E> {

    private E[] stack;
    private int n;

    @SuppressWarnings("unchecked")
    public ResizableArrayStack() {
        this.stack = (E[]) new Object[1];
        this.n = 0;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void push(E item) {
        if (n == stack.length) {
            resize(2 * stack.length); // repeated doubling
        }

        stack[n++] = item;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        E item = stack[--n];
        stack[n] = null; // avoid loitering to allow GC reclaim memory

        // the stack is always between 25% and 100% full
        if (n > 0 && n == stack.length / 4) {
            resize(stack.length / 2); // thrashing - push-pop-push-pop when an array is full
        }

        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] copy = (E[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = stack[i];
        }
        stack = copy;
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
            if (!hasNext()) throw new NoSuchElementException();
            return stack[--i];
        }
    }
}
