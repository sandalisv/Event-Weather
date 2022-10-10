/*
 * Copyright (c) 10/10/22, 2:37 AM Created by Sandali Vithanage.
 */

package com.events.event.management.exception;

/**
 * The type Custom db exception.
 */
public class CustomDbException extends Exception{
    private static final String DATABASE_EXCEPTION = "Database error occurred";

    /**
     * Instantiates a new Custom db exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public CustomDbException(String message, Throwable cause) {
        super(DATABASE_EXCEPTION +" - "+ message, cause);
    }

    public CustomDbException(String message) {
        super(DATABASE_EXCEPTION +" - "+ message);
    }
}
