package org.home.algorithms.stack;

import java.util.Iterator;

/**
 * Memory usage
 * <p>
 * 40 bytes per stack node:
 * <ul>
 *     <li>16 bytes (object overhead)</li>
 *     <li>8 bytes (inner class extra overhead)</li>
 *     <li>8 bytes (reference to E)</li>
 *     <li>8 bytes (reference to Node)</li>
 * </ul>
 * <p>
 * Analysis include memory for the stack (but not the items themselves)
 * <p>
 * Pros:
 * <ul>
 *     <li>every operation takes constant time in the worst case</li>
 * </ul>
 * <p>
 * Cons:
 * <ul>
 *     <li>uses extra time and space to deal with the links</li>
 * </ul>
 */
public class LinkedListStack<E> implements Stack<E> {

    private Node head;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(E item) {
        head = new Node(item, head);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty stack");
        }

        E item = head.item;
        head = head.next;

        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class Node {

        private final E item;
        private final Node next;

        private Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node current = head;

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
}
