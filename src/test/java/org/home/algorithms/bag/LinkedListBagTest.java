package org.home.algorithms.bag;

import java.util.List;

class LinkedListBagTest implements BagContract<String, Bag<String>, List<String>> {

    @Override
    public Bag<String> createInstance() {
        return new LinkedListBag<>();
    }

    @Override
    public List<String> createTestItems() {
        return List.of("a", "b", "c");
    }
}