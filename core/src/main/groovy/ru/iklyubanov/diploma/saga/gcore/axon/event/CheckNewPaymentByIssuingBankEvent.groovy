package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

import javax.validation.constraints.NotNull

/**
 * Created by ivan on 12/13/2015.
 */
@Event
class CheckNewPaymentByIssuingBankEvent {
    @NotNull
    String transactionId
    @NotNull
    String issuingBankBIK
    /**
     * К оплате
     */
    @NotNull
    BigDecimal amount
    /**
     * Валюта
     */
    @NotNull
    String currencyType
    /**Код карты*/
    @NotNull
    String code
    /**
     * Имя держателя карты
     */
    @NotNull
    String firstName
    /**
     * Фамилия держателя карты
     */
    @NotNull
    String lastName
    /**Дата окончания срока действия карты*/
    @NotNull
    String expiredDate
    /** Код безопасности (CVC2, CVV2, CID, CSC)*/
    @NotNull
    String ccvCode
}
