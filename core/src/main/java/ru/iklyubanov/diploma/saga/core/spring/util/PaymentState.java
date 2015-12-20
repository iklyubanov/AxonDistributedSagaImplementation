package ru.iklyubanov.diploma.saga.core.spring.util;

/**
 * Created by kliubanov on 24.11.2015.
 */
public enum PaymentState {
    COMPLETED("завершен"),
    REJECTED("откланен"),
    PROCESSED("в обработке"),
    NEW("новый"),
    WITHDRAW("с клиента сняты деньги"),
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
