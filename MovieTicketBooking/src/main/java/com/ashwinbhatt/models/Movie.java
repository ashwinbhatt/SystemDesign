package com.ashwinbhatt.models;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Movie {
    private final String movieName;
    private final Date duration;
}
