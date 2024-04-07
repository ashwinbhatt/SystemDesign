package com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.DeliveryUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.DeliveryUser;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Item;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Locker;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.DeliveryUserRepo;

import java.util.List;

public class RandomDeliveryUserAllocator implements IDeliveryUserAllocator{

    private final DeliveryUserRepo deliveryUserRepo;


    public RandomDeliveryUserAllocator(DeliveryUserRepo deliveryUserRepo) {
        this.deliveryUserRepo = deliveryUserRepo;
    }

    public DeliveryUser getDeliveryUser(Integer startDate, Locker locker, Item item) {
        List<String> allDeliveryUser = deliveryUserRepo.getAllDeliveryUser();
        int sz = allDeliveryUser.size();
        int ran = (int)(Math.random() * sz);
        try {
            return deliveryUserRepo.getDeliveryUser(allDeliveryUser.get(ran));
        } catch (DeliveryUserRepoException e) {
            throw new RuntimeException(e);
        }
    }
}
