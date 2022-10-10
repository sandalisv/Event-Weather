/*
 * Copyright (c) 10/10/22, 2:38 AM Created by Sandali Vithanage.
 */

package com.events.event.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Weather {
    public int id;
    public String main;
    public String description;
    @JsonIgnore
    public String icon;
}
