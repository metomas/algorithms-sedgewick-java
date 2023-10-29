package org.home.algorithms.bag;

import java.util.Iterator;

public interface Bag<E> extends Iterable<E> {

    void add(E item);

    int size();

    Iterator<E> iterator();

}
