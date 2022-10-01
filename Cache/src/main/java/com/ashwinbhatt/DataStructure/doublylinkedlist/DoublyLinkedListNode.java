package com.ashwinbhatt.DataStructure.doublylinkedlist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoublyLinkedListNode<E> {
    private E data;
    private DoublyLinkedListNode<E> previousNode, nextNode;
}
