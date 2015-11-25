package ru.iklyubanov.diploma.saga.jpa.util;

/**
 * Created by kliubanov on 24.11.2015.
 */
public enum PaymentState {
    COMPLETED,
    REJECTED,
    PROCESSED,
    NEW,
    ERROR
}
