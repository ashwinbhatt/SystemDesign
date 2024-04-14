package com.ashwinbhatt.systemdesign.movieticketbooking.strategies;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.Booking;

public interface IPaymentStrategy {

    public boolean doPayment(Booking booking, IPaymentObject paymentObject);

    public boolean doRefund(Booking booking, IPaymentObject paymentObject);
}
