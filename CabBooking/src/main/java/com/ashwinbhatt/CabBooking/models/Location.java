package com.ashwinbhatt.CabBooking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Location {
    private Double x, y;

    public double euclidDistance(Location l2){
        double xaxisDis= l2.getX()-this.x;
        double yaxisDis= l2.getY()-this.y;
        return xaxisDis*xaxisDis + yaxisDis*yaxisDis;
    }
}
