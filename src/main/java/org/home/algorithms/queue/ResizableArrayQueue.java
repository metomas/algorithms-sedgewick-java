package org.home.algorithms.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizableArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 8;
    private static final int DEFAULT_HEAD_INDEX = 0;
    private static final int DEFAULT_TAIL_INDEX = 0;

    private E[] queue;
    private int head, tail;

    @SuppressWarnings("unchecked")
    public ResizableArrayQueue() {
        this.queue = (E[]) new Object[DEFAULT_CAPACITY];
        this.head = DEFAULT_HEAD_INDEX;
        this.tail = DEFAULT_TAIL_INDEX;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public void enqueue(E item) {
        if (tail == queue.length) resize(2 * queue.length); // repeated doubling

        queue[tail++] = item;
    }

    @Override
    public E dequeue() throws IllegalStateException {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        E item = queue[head];
        queue[head++] = null; // avoid loitering

        if (isEmpty()) {
            resize(DEFAULT_CAPACITY);
        } else if (size() == queue.length / 4) { // array is between 25% and 100% full
            resize(queue.length / 2);
        }

        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator();
    }

    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        E[] copy = (E[]) new Object[capacity];
        for (int i = head, j = 0; i < tail; i++) {
            copy[j++] = queue[i];
        }

        queue = copy;
        tail = size();
        head = DEFAULT_HEAD_INDEX;
    }

    @Override
    public int size() {
        return tail - head;
    }

    private class ArrayQueueIterator implements Iterator<E> {

        private int h = head;
        private int t = tail;

        @Override
        public boolean hasNext() {
            return h != t;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            return queue[h++];
        }
    }
}
