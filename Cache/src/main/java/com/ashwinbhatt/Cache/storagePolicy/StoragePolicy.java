package com.ashwinbhatt.Cache.storagePolicy;

import com.ashwinbhatt.Cache.exceptions.StorageFullException;
import com.ashwinbhatt.Cache.exceptions.StoragePolicyException;

public interface StoragePolicy<Key, Value> {

    Value getKeyValue(Key key) throws StoragePolicyException;

    void addKeyValue(Key key, Value value) throws StorageFullException;

    void removeKeyValue(Key key) throws StoragePolicyException;
}
