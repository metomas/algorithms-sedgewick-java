package org.home.algorithms.queue;

import java.util.NoSuchElementException;

/**
 * Time complexity
 * <ul>
 *     <li>insert: O(1)</li>
 *     <li>delete max: O(N)</li>
 *     <li>max: O(N)</li>
 * </ul>
 */
public class UnorderedArrayMaxPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private E[] queue;
    private int n = 0;

    @SuppressWarnings("unchecked")
    public UnorderedArrayMaxPriorityQueue() {
        this.queue = (E[]) new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void insert(E item) {
        if (n == queue.length) resize(2 * n);
        queue[n++] = item;
    }

    @Override
    public E max() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return queue[maxIndex()];
    }

    @Override
    public E deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        swap(maxIndex(), --n);
        E item = queue[n];
        queue[n] = null;

        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);

        return item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int size) {
        @SuppressWarnings("unchecked")
        E[] copy = (E[]) new Comparable[size];
        if (n >= 0) System.arraycopy(queue, 0, copy, 0, n);
        queue = copy;
    }

    private int maxIndex() {
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (less(queue[max], queue[i])) {
                max = i;
            }
        }
        return max;
    }

    private <T extends Comparable<T>> boolean less(T t, T t2) {
        return t.compareTo(t2) < 0;
    }

    private void swap(int i, int j) {
        E aux = queue[i];
        queue[i] = queue[j];
        queue[j] = aux;
    }
}
