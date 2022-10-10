/*
 * Copyright (c) 10/10/22, 2:36 AM Created by Sandali Vithanage.
 */

package com.events.event.management.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Event guest.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "event_guest")
public class EventGuest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reference_id")
    private int reference_id;

    @Column(name = "event_id")
    private int event_id;

    @Column(name = "guest_id")
    private int guest_id;
}
