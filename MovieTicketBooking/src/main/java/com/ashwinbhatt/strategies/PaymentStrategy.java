package com.ashwinbhatt.strategies;

import java.util.List;

public interface PaymentStrategy {

    public Integer getCost(List<Integer> seatNumbers);
    public boolean doPayment(Integer cost);
}
