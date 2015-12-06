package ru.iklyubanov.diploma.saga.core.spring.util;

/**
 * Created by kliubanov on 24.11.2015.
 */
public enum PaymentType {
    CONSUMER_CREDIT("Потребительский кредит"),
    CREDIT("Кредит"),
    CAR_CREDIT("Автокредит"),
    MORTAGE_PAYMENT("Ипотека"),
    CONSUMER_PAYMENT("Потребительский платеж"),
    BILL_PAYMENT("Оплата счетов"),
    MONEY_TRANSFER("Денежный перевод"),
    INVESTMENT("Инвестирование"),
    CHARITY("Благотворительность");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
