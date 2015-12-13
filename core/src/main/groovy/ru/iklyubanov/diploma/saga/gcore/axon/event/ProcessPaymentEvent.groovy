package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by ivan on 12/13/2015.
 */
@Event
public class ProcessPaymentEvent {
    String transactionId
    /**
     * К оплате
     */
    BigDecimal amount
    /**
     * Валюта
     */
    String currencyType

    /**
     * Тип платежа
     */
    String paymentType

    /**
     * Код карты
     */
    String code

    /**
     * Имя держателя карты
     */
    String firstName
    /**
     * Фамилия держателя карты
     */
    String lastName
    /**Дата окончания срока действия карты*/
    String expiredDate
    /** Код безопасности (CVC2, CVV2, CID, CSC)*/
    String ccvCode
}
