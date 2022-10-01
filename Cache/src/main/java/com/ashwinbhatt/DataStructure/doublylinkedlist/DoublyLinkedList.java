package com.ashwinbhatt.DataStructure.doublylinkedlist;

import com.ashwinbhatt.DataStructure.exceptions.NodeNotFound;

public class DoublyLinkedList<E> {

    private DoublyLinkedListNode<E> front, back;
    private Integer size;

    public DoublyLinkedList(){
        size=0;
    }

    public int size(){
        return size.intValue();
    }

    public DoublyLinkedListNode<E> getFront() throws NodeNotFound {
        if(front == null){
            throw new NodeNotFound("There are no nodes");
        }
        return front;
    }

    public DoublyLinkedListNode<E> getLast() throws NodeNotFound {
        if(back == null){
            throw new NodeNotFound("There are no nodes");
        }
        return back;
    }

    public void addFront(DoublyLinkedListNode<E> newNode) {
        size++;
        if(size==1){
            front= back= newNode;
            return;
        }
        newNode.setNextNode(front);
        newNode.setPreviousNode(null);
        front= newNode;
    }

    public void addBack(DoublyLinkedListNode<E> newNode) {
        size++;
        if(size==1){
            front=back= newNode;
            return;
        }
        newNode.setPreviousNode(back);
        newNode.setNextNode(null);
        back= newNode;
    }

    public void removeNode(DoublyLinkedListNode<E> node){
        size--;
        DoublyLinkedListNode<E> pre= node.getPreviousNode(), next= node.getNextNode();
        if(pre != null){
            pre.setNextNode(next);
        }
        if(next != null){
            next.setPreviousNode(pre);
        }
        node.setNextNode(null);
        node.setPreviousNode(null);
    }

    public void removeAppendBack(DoublyLinkedListNode<E> node){
        removeNode(node);
        addBack(node);
    }

}
