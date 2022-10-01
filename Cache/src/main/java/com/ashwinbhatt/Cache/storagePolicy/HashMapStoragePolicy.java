package com.ashwinbhatt.Cache.storagePolicy;

import com.ashwinbhatt.Cache.exceptions.StorageFullException;
import com.ashwinbhatt.Cache.exceptions.StoragePolicyException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStoragePolicy<Key, Value> implements StoragePolicy<Key, Value> {

    Map<Key, Value> storage= new HashMap<>();
    private final Integer storageCapacity;

    public HashMapStoragePolicy(Integer storageCapacity){
       storage= new HashMap<>();
       this.storageCapacity= storageCapacity;
    }

    public boolean isStorageCapacityFull(){
        return storageCapacity == storage.size();
    }

    @Override
    public Value getKeyValue(Key key) throws StoragePolicyException {
        Value value= storage.get(key);
        if(value == null){
            throw new StoragePolicyException("Key: "+key +" not found in storage");
        }
        return value;
    }

    @Override
    public void addKeyValue(Key key, Value value) throws StorageFullException {
        if(!storage.containsKey(key) && isStorageCapacityFull()){
            throw new StorageFullException("Storage capacity full!!");
        }
        storage.put(key, value);
    }

    @Override
    public void removeKeyValue(Key key) throws StoragePolicyException {
        if(!storage.containsKey(key)){
            throw new StoragePolicyException("Key: "+key+" not foudn in storage");
        }
        storage.remove(key);
    }
}
