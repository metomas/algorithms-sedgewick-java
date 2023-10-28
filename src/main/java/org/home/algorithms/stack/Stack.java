package org.home.algorithms.stack;

public interface Stack<E> extends Iterable<E> {

    boolean isEmpty();

    void push(E item);

    E pop();

}