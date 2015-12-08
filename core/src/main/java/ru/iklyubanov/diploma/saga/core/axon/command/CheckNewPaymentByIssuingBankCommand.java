package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class CheckNewPaymentByIssuingBankCommand {

    @TargetAggregateIdentifier
    private final String bankCardId;
    private final TransactionId transactionId;

    /**
     * Тип карты (PaymentSystemType)
     */
    private String paymentSystemType;
    /**
     * Код карты
     */
    private String code;
    /**
     * Имя держателя карты
     */
    private String firstName;
    /**
     * Фамилия держателя карты
     */
    private String lastName;
    /**
     * Дата окончания срока действия карты
     */
    private String expiredDate;
    /**
     * Код безопасности (CVC2, CVV2, CID, CSC)
     */
    private String ccvCode;

    public CheckNewPaymentByIssuingBankCommand(String bankCardId, TransactionId transactionId) {
        this.bankCardId = bankCardId;
        this.transactionId = transactionId;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public String getPaymentSystemType() {
        return paymentSystemType;
    }

    public void setPaymentSystemType(String paymentSystemType) {
        this.paymentSystemType = paymentSystemType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCcvCode() {
        return ccvCode;
    }

    public void setCcvCode(String ccvCode) {
        this.ccvCode = ccvCode;
    }
}
