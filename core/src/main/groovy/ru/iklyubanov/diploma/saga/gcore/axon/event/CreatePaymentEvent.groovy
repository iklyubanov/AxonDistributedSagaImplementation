package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by kliubanov on 27.11.2015.
 */
@Event
public class CreatePaymentEvent {

    TransactionId transactionId
    /**
     * Тип карты (PaymentSystemType)
     */
    String paymentSystemType
    /**
     * К оплате
     */
    BigDecimal amount
    /**
     * Валюта
     */
    String currencyType
    /**Код карты*/
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
    /** Тип платежа*/
    String paymentType
    /** БИК банка получателя*/
    String merchantBankBIK
    /** Номер счета в банке-получателе*/
    String merchantBankAccount
    /** Получатель (не > 160 симв)*/
    String merchant
    /** ИНН (не > 160 симв)*/
    String merchantINN

    int timeout

    private CreatePaymentEvent(TransactionId transactionId) {
        this.transactionId = transactionId
    }

    public static class Builder {

        private CreatePaymentEvent createPaymentEvent

        public Builder(TransactionId transactionId) {
            this.createPaymentEvent = new CreatePaymentEvent(transactionId)
        }

        public CreatePaymentEvent build() {
            createPaymentEvent
        }

        public Builder addPaymentSystemType(String paymentSystemType) {
            createPaymentEvent.paymentSystemType = paymentSystemType
            this
        }

        public Builder addAmount(BigDecimal amount) {
            createPaymentEvent.amount = amount
            this
        }

        public Builder addCurrencyType(String currencyType) {
            createPaymentEvent.currencyType = currencyType
            this
        }

        public Builder addCode(String code) {
            createPaymentEvent.code = code
            this
        }

        public Builder addFirstName(String firstName) {
            createPaymentEvent.firstName = firstName
            this
        }

        public Builder addLastName(String lastName) {
            createPaymentEvent.lastName = lastName
            this
        }

        public Builder addExpiredDate(String expiredDate) {
            createPaymentEvent.expiredDate = expiredDate
            this
        }

        public Builder addCcvCode(String ccvCode) {
            createPaymentEvent.ccvCode = ccvCode
            this
        }

        public Builder addPaymentType(String paymentType) {
            createPaymentEvent.paymentType = paymentType
            this
        }

        public Builder addMerchantBankBIK(String merchantBankBIK) {
            createPaymentEvent.merchantBankBIK = merchantBankBIK
            this
        }

        public Builder addMerchantBankAccount(String merchantBankAccount) {
            createPaymentEvent.merchantBankAccount = merchantBankAccount
            this
        }

        public Builder addMerchant(String merchant) {
            createPaymentEvent.merchant = merchant
            this
        }

        public Builder addMerchantINN(String merchantINN) {
            createPaymentEvent.merchantINN = merchantINN
            this
        }
    }
}
