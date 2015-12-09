package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Step 2: YapStone to Payment Processor

 YapStone sends the transaction details to our payment processor using a dedicated link that’s monitored 24 x 7 x 365
 to ensure that processing is not interrupted.

 Step 3: Payment Processor to Card Networks to Card Issuing Bank

 Payment details are validated by the payment processor by sending them through the credit or debit card networks
 (Visa, MasterCard, American Express, Discover), which forward them to the card-issuing bank to be authorized.

 Этой комадной следует передавать информацию о плательщике

 * Created by kliubanov on 27.11.2015.
 */
public class ProcessPaymentByProcessorCommand {

    @TargetAggregateIdentifier
    private final String transactionId;

    /**
     * К оплате
     */
    private BigDecimal amount;
    /**
     * Валюта
     */
    private String currencyType;

    /**
     * Тип платежа
     */
    private String paymentType;

    /**
     * Код карты
     */
    @NotNull
    private String code;

    /**
     * Имя держателя карты
     */
    private String firstName;
    /**
     * Фамилия держателя карты
     */
    private String lastName;
    /**Дата окончания срока действия карты*/
    private String expiredDate;
    /** Код безопасности (CVC2, CVV2, CID, CSC)*/
    private String ccvCode;

    public ProcessPaymentByProcessorCommand(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
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
