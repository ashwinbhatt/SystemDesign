package com.ashwinbhatt.systemdesign.movieticketbooking.repositories;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.Show;

import java.util.HashMap;
import java.util.Map;

public class ShowRepo {

    private final Map<String, Show> showMap;

    public ShowRepo() {
        this.showMap = new HashMap<>();
    }

    public Show getShow(String showId) {
        return showMap.get(showId);
    }

    public Show createShow(Show show) {
        showMap.put(show.getShowId(), show);
        return show;
    }
}
