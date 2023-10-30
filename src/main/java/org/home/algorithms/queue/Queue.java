package org.home.algorithms.queue;

public interface Queue<E> extends Iterable<E> {

    boolean isEmpty();

    void enqueue(E item);

    E dequeue();

    int size();

}
