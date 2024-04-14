package com.ashwinbhatt.systemdesign.movieticketbooking.repositories;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenRepo {

    private final Map<String, List<Screen>> screens;

    public ScreenRepo() {
        this.screens = new HashMap<>();
    }

    public Screen getScreen(String cinemaId, int screenNo) {
        if(!screens.containsKey(cinemaId)) {
            return null;
        }
        for(Screen screen: screens.get(cinemaId)) {
            if (screen.getScreenNumber() == screenNo) {
                return screen;
            }
        }
        return null;
    }

    public Screen createScreen(Screen screen) {
        String cinemaId = screen.getCinema().getId();
        if(!screens.containsKey(cinemaId)) {
            screens.put(cinemaId, new ArrayList<>());
        }
        screens.get(cinemaId).add(screen);
        return screen;
    }
}
