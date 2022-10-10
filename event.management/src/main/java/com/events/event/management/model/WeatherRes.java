/*
 * Copyright (c) 10/10/22, 2:39 AM Created by Sandali Vithanage.
 */

package com.events.event.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherRes {
    public String cod;
    public int message;
    @JsonIgnore
    public int cnt;
    @JsonProperty("list")
    public ArrayList<WeatherList> weatherList;
    @JsonIgnore
    public String city;
}
