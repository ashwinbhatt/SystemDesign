package com.ashwinbhatt.strategies;

import java.util.List;
import java.util.Random;

public class SimplePaymentStrategy implements PaymentStrategy {

    @Override
    public Integer getCost(List<Integer> seatNumbers) {
        return 10*seatNumbers.size();
    }

    @Override
    public boolean doPayment(Integer cost) {
        return Math.random() > 0.80;
    }
}
