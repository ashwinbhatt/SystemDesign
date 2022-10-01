package com.ashwinbhatt.Cache.evictionPolicy;

import com.ashwinbhatt.Cache.exceptions.EvictionPolicyException;
import com.ashwinbhatt.DataStructure.exceptions.NodeNotFound;

public interface EvictionPolicy<Key> {

    void keyAccessed(Key key);

    Key evictKey() throws EvictionPolicyException, NodeNotFound;
}
