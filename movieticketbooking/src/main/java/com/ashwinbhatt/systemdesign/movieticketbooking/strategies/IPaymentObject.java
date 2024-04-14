package com.ashwinbhatt.systemdesign.movieticketbooking.strategies;

public interface IPaymentObject {

    public boolean doPayment(Double amount);

    public boolean doRefund(Double amount);
}
