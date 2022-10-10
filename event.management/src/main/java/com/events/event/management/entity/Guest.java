/*
 * Copyright (c) 10/10/22, 2:36 AM Created by Sandali Vithanage.
 */

package com.events.event.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Guest.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guest")
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private int guest_id;

    @Column(name = "guest_name")
    private String guest_name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE},
            mappedBy = "guestSet")
    @JsonIgnore
    private Set<Event> eventSet = new HashSet<>();


}
