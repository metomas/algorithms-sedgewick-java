package org.home.algorithms.queue;

import java.util.NoSuchElementException;

/**
 * Time complexity
 * <ul>
 *     <li>insert: O(N)</li>
 *     <li>delete max: O(1)</li>
 *     <li>max: O(1)</li>
 * </ul>
 */
public class OrderedArrayMaxPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private E[] queue;
    private int n = 0;

    @SuppressWarnings("unchecked")
    public OrderedArrayMaxPriorityQueue() {
        this.queue = (E[]) new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void insert(E item) {
        if (n == queue.length) resize(2 * n);
        queue[n++] = item;
        sort();
    }

    @Override
    public E max() {
        return isEmpty() ? null : queue[n - 1];
    }

    @Override
    public E deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        E item = queue[--n];
        queue[n] = null;

        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);

        return item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private void sort() {
        for (int i = n - 1; i > 0 && less(queue[i], queue[i - 1]); i--) swap(i, i - 1);
    }

    private void resize(int size) {
        @SuppressWarnings("unchecked")
        E[] copy = (E[]) new Comparable[size];
        if (n >= 0) System.arraycopy(queue, 0, copy, 0, n);
        queue = copy;
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
