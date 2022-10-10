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
public class GeoCode {
    public String name;
    @JsonIgnore
    public String local_names;
    public double lat;
    public double lon;
    public String country;

}
