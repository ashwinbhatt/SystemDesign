package com.ashwinbhatt.systemdesign.movieticketbooking.strategies;

import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.ShowAllocationStrategyException;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Show;

public interface IShowStrategy {

    public boolean checkShowIsViable(Show show) throws ShowAllocationStrategyException;

    public void allocateShow(Show show) throws ShowAllocationStrategyException;


}
