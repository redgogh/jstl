package com.redgogh.tools.cache;

import com.redgogh.tools.StreamMapper;

public interface LocalCache<K, V> {

    void put(K key, V value);

    void put(K key, V value, int expire);

    V get(K key);

    V get(K key, StreamMapper<Object, V> mapping);

    void remove(K key);

    boolean containsKey(K key);

    int size();

}
