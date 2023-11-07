package org.home.algorithms.queue;

public interface MaxPriorityQueue<E extends Comparable<E>> {

    void insert(E item);

    E max();

    E deleteMax();

    boolean isEmpty();

}
