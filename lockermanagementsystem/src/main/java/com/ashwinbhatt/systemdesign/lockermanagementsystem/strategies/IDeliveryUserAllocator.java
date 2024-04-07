package com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.DeliveryUser;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Item;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Locker;

public interface IDeliveryUserAllocator {

    public DeliveryUser getDeliveryUser(Integer startDate, Locker locker, Item item);
}
