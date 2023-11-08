package org.home.algorithms.queue;

import java.util.NoSuchElementException;

/**
 * <p>Array representation of a heap-ordered complete binary tree (parent's key no smaller than children's keys).</p>
 * <p>
 * Time complexity
 * <ul>
 *     <li>insert: O(logN)</li>
 *     <li>delete max: O(logN)</li>
 *     <li>max: O(1)</li>
 * </ul>
 * </p>
 */
public class BinaryHeapMaxPriorityQueue<E extends Comparable<E>> implements MaxPriorityQueue<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private E[] queue;
    private int n = 0;

    @SuppressWarnings("unchecked")
    public BinaryHeapMaxPriorityQueue() {
        this.queue = (E[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    // at most 1 + lgN compares
    @Override
    public void insert(E item) {
        if (n == queue.length - 1) resize(2 * n + 1);
        queue[++n] = item;
        swimup(n);
    }

    @Override
    public E max() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return queue[1];
    }

    // at most 2lgN compares
    @Override
    public E deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        E item = queue[1];
        swap(1, n--);
        sink(1);
        queue[n + 1] = null;

        if (n == (queue.length - 1) / 4) resize(((queue.length - 1) / 2) + 1);

        return item;
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(queue[j], queue[j + 1])) j++;
            if (!less(queue[k], queue[j])) break;
            swap(k, j);
            k = j;
        }
    }

    private void swimup(int k) {
        while (k > 1 && less(queue[k / 2], queue[k])) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int size) {
        @SuppressWarnings("unchecked")
        E[] copy = (E[]) new Comparable[size];
        for (int i = 0; i <= n; i++) copy[i] = queue[i];
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
