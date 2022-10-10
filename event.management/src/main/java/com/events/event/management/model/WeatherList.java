/*
 * Copyright (c) 10/10/22, 2:39 AM Created by Sandali Vithanage.
 */

package com.events.event.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class WeatherList {
    @JsonIgnore
    public int dt;
    @JsonIgnore
    public String main;
    @JsonProperty("weather")
    public ArrayList<Weather> weather;
    @JsonIgnore
    public String clouds;
    @JsonIgnore
    public String wind;
    @JsonIgnore
    public int visibility;
    @JsonIgnore
    public double pop;
    @JsonIgnore
    public String rain;
    @JsonIgnore
    public String sys;
    public String dt_txt;
}
