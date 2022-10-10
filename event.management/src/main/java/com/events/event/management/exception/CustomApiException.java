/*
 * Copyright (c) 10/10/22, 2:37 AM Created by Sandali Vithanage.
 */

package com.events.event.management.exception;

/**
 * The type Custom api exception.
 */
public class CustomApiException extends Exception {
    private static final String API_EXCEPTION = "Error while calling external API";

    /**
     * Instantiates a new Custom api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public CustomApiException(String message, Throwable cause) {
        super(API_EXCEPTION + " - " + message, cause);
    }

}
