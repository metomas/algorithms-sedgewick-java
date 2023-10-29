package org.home.algorithms.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListBag<E> implements Bag<E> {

    private Node<E> head;
    private int n;

    public LinkedListBag() {
        n = 0;
    }

    @Override
    public void add(E item) {
        head = new Node<>(item, head);
        n++;
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

        private Node<E> current;

        private LinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            E item = current.item;
            current = current.next;

            return item;
        }
    }

    private record Node<E>(E item, Node<E> next) {
    }
}
