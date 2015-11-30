package ru.iklyubanov.diploma.saga.jpa.util;

/**
 * Created by kliubanov on 24.11.2015.
 */
public enum PaymentState {
    COMPLETED("завершен"),
    REJECTED("откланен"),
    PROCESSED("в обработке"),
    NEW("новый"),
    ERROR("ошибка");

    private final String type;

    PaymentState(String value) {
        this.type = value;
    }

    @Override
    public String toString() {
        return type;
    }
}
