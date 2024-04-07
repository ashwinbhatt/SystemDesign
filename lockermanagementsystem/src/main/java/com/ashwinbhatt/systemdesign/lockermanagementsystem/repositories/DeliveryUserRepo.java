package com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.DeliveryUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.DeliveryUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliveryUserRepo {

    private final Map<String, DeliveryUser> deliveryUserRepoMap;

    public DeliveryUserRepo() {
        this.deliveryUserRepoMap = new HashMap<String, DeliveryUser>();
    }

    public void addDeliveryUser(DeliveryUser deliveryUser) throws DeliveryUserRepoException {
        if(deliveryUserRepoMap.containsKey(deliveryUser.getUserId())) {
            throw new DeliveryUserRepoException(String.format("Delivery user with id: <%s>, already exist", deliveryUser.getUserId()));
        }
        deliveryUserRepoMap.put(deliveryUser.getUserId(), deliveryUser);
    }

    public DeliveryUser getDeliveryUser(String deliveryUserId) throws DeliveryUserRepoException {
        DeliveryUser deliveryUser = deliveryUserRepoMap.get(deliveryUserId);
        if(deliveryUser == null) {
            throw new DeliveryUserRepoException(String.format("Delivery user with id: <%s>, don't exist", deliveryUser.getUserId()));
        }
        return deliveryUser;
    }

    public List<String> getAllDeliveryUser() {
        return new ArrayList<>(deliveryUserRepoMap.keySet());
    }
}
