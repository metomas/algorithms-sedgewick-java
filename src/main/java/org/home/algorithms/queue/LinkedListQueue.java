package org.home.algorithms.queue;

import java.util.Iterator;

public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int n = 0;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void enqueue(E item) {
        final Node<E> newNode = new Node<>(item, null);

        if (isEmpty()) head = newNode;
        else tail.next = newNode;

        tail = newNode;
        n++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty queue");
        }

        E item = head.item;
        head = head.next;

        if (isEmpty()) tail = null;
        n--;

        return item;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E item = current.item;
            current = current.next;

            return item;
        }
    }

    private static class Node<E> {

        private final E item;
        private Node<E> next;

        private Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

}
