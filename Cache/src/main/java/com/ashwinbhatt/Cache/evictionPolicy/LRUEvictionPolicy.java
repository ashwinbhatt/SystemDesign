package com.ashwinbhatt.Cache.evictionPolicy;

import com.ashwinbhatt.Cache.exceptions.EvictionPolicyException;
import com.ashwinbhatt.DataStructure.doublylinkedlist.DoublyLinkedList;
import com.ashwinbhatt.DataStructure.doublylinkedlist.DoublyLinkedListNode;
import com.ashwinbhatt.DataStructure.exceptions.NodeNotFound;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    private DoublyLinkedList<Key> doublyLinkedList;
    private Map<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy(){
        this.doublyLinkedList= new DoublyLinkedList<>();
        this.mapper= new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key){
        if(mapper.containsKey(key)){
            DoublyLinkedListNode<Key> doublyLinkedListNode= mapper.get(key);
            doublyLinkedList.removeAppendBack(doublyLinkedListNode);
        }else{
            DoublyLinkedListNode<Key> newNode= new DoublyLinkedListNode<>();
            newNode.setData(key);
            doublyLinkedList.addBack(newNode);
        }
    }

    @Override
    public Key evictKey() throws EvictionPolicyException, NodeNotFound {
        if(doublyLinkedList.size() == 0){
            throw new EvictionPolicyException("Eviction policy does not have any key");
        }

        DoublyLinkedListNode<Key> evictedKey= doublyLinkedList.getFront();
        doublyLinkedList.removeNode(evictedKey);

        mapper.remove(evictedKey.getData());
        return evictedKey.getData();
    }
}
