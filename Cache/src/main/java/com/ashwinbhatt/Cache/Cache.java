package com.ashwinbhatt.Cache;

import com.ashwinbhatt.Cache.exceptions.StorageFullException;
import com.ashwinbhatt.Cache.evictionPolicy.EvictionPolicy;
import com.ashwinbhatt.Cache.exceptions.EvictionPolicyException;
import com.ashwinbhatt.Cache.exceptions.StoragePolicyException;
import com.ashwinbhatt.Cache.storagePolicy.StoragePolicy;
import com.ashwinbhatt.DataStructure.exceptions.NodeNotFound;

public class Cache<Key, Value> {
    private final StoragePolicy<Key, Value> storagePolicy;
    private final EvictionPolicy<Key> evictionPolicy;


    public Cache(StoragePolicy<Key, Value> storagePolicy, EvictionPolicy<Key> evictionPolicy) {
        this.storagePolicy = storagePolicy;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key key, Value value) {
        try {
            storagePolicy.addKeyValue(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            try {
                Key evictedKey= evictionPolicy.evictKey();
                storagePolicy.removeKeyValue(evictedKey);

                storagePolicy.addKeyValue(key, value);
                evictionPolicy.keyAccessed(key);
            } catch (StorageFullException | EvictionPolicyException | NodeNotFound | StoragePolicyException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public Value get(Key key) {
        Value value= null;
        try {
            value = storagePolicy.getKeyValue(key);
        } catch (StoragePolicyException e) {
            System.out.println(e.getMessage());
            return null;
        }
        evictionPolicy.keyAccessed(key);
        return value;
    }
}
