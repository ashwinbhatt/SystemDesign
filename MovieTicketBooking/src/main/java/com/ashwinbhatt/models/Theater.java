package com.ashwinbhatt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Theater {
    private List<Screen> screens;

    public Integer addScreen(Integer screenCapacity){
        Screen screen= new Screen(screens.size(), screenCapacity);
        screens.add(screen);
        return screens.size()-1;
    }
}
