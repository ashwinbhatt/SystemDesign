package com.ashwinbhatt.Cache.factories;

import com.ashwinbhatt.Cache.Cache;
import com.ashwinbhatt.Cache.evictionPolicy.LRUEvictionPolicy;
import com.ashwinbhatt.Cache.storagePolicy.HashMapStoragePolicy;

public class CacheFactories <Key, Value>{

    public Cache<Key, Value> defaultCache(final int capacity){
        return new Cache<Key, Value>(new HashMapStoragePolicy<Key, Value>(capacity),
                new LRUEvictionPolicy<Key>());
    }
}
