/*
 * Copyright (c) 2022-10/10/22, 2:20 AM Created by Sandali Vithanage.
 */

package com.events.event.management.dao;

import com.events.event.management.entity.Event;
import com.events.event.management.exception.CustomDbException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of Event repository.
 */
@Transactional
@Repository
public class EventDAOImpl implements EventDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Event> getEvents() throws CustomDbException {
        String hql = "FROM Event as evnt ORDER BY evnt.id";
        try {
            if (entityManager.createQuery(hql).getResultList() == null || entityManager.createQuery(hql).getResultList().isEmpty())
                throw new CustomDbException("No events are there in the database.");
            else return entityManager.createQuery(hql).getResultList();
        } catch (Exception e) {
            throw new CustomDbException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public Event getEvent(int eventId) throws CustomDbException {
        try {
            if (entityManager.find(Event.class, eventId) != null)
                return entityManager.find(Event.class, eventId);
            else throw new CustomDbException("Event with id "+eventId+" does not exist in the database.");
        } catch (Exception e) {
            throw new CustomDbException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Event createEvent(Event event) throws CustomDbException {
        try {
            entityManager.persist(event);
            return getLastInsertedEvent();
        } catch (Exception e) {
            throw new CustomDbException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean deleteEvent(int id) throws CustomDbException {
        try {
            Event event = getEvent(id);
            if (event != null) {
                entityManager.remove(event);

                boolean status = entityManager.contains(event);
                if (status) {
                    return false;
                } else {
                    return true;
                }
            }
            else throw new CustomDbException("Event does not exist in the database.");
        } catch (Exception e) {
            throw new CustomDbException(e.getMessage(), e.getCause());
        }
    }

    private Event getLastInsertedEvent() throws CustomDbException {
        String hql = "from Event order by event_id DESC";
        try {
            Query query = entityManager.createQuery(hql);
            query.setMaxResults(1);
            return (Event) query.getSingleResult();
        } catch (Exception e) {
            throw new CustomDbException(e.getMessage(), e.getCause());
        }
    }
}
