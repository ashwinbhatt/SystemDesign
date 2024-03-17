package com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.DeliveryUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.ItemRepositoryException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemRepo {

    private final Map<String, Item> itemMap;

    public ItemRepo(){
        itemMap = new HashMap<String, Item>();
    }

    public void addItem(Item item) throws DeliveryUserRepoException {
        if(itemMap.containsKey(item.getItemId())) {
            throw new DeliveryUserRepoException(String.format("Item with id: <%s>, already exist", item.getItemId()));
        }
        itemMap.put(item.getItemId(), item);
    }

    public Item getItem(String id) throws DeliveryUserRepoException {
        Item item = itemMap.get(id);
        if(item == null) {
            throw new DeliveryUserRepoException(String.format("Item with id: <%s>, don't exist", item.getItemId()));
        }
        return item;
    }
}
