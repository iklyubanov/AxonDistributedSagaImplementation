package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

import java.math.BigDecimal;

/**
 * Step 2: YapStone to Payment Processor

 YapStone sends the transaction details to our payment processor using a dedicated link that’s monitored 24 x 7 x 365
 to ensure that processing is not interrupted.

 Step 3: Payment Processor to Card Networks to Card Issuing Bank

 Payment details are validated by the payment processor by sending them through the credit or debit card networks
 (Visa, MasterCard, American Express, Discover), which forward them to the card-issuing bank to be authorized.

 * Created by kliubanov on 27.11.2015.
 */
public class ProcessPaymentByProcessorCommand {

    /*@TargetAggregateIdentifier TODO вроде как не нужен
    private final String processorId;*/
    private final TransactionId transactionId;

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
     * БИК банка получателя
     */
    private String merchantBankBIK;
    /**
     * Номер счета в банке-получателе
     */
    private String merchantBankAccount;
    /**
     * Получатель (не > 160 симв)
     */
    private String merchant;
    /**
     * ИНН (не > 160 симв)
     */
    private String merchantINN;

    public ProcessPaymentByProcessorCommand( TransactionId transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getMerchantBankBIK() {
        return merchantBankBIK;
    }

    public void setMerchantBankBIK(String merchantBankBIK) {
        this.merchantBankBIK = merchantBankBIK;
    }

    public String getMerchantBankAccount() {
        return merchantBankAccount;
    }

    public void setMerchantBankAccount(String merchantBankAccount) {
        this.merchantBankAccount = merchantBankAccount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getMerchantINN() {
        return merchantINN;
    }

    public void setMerchantINN(String merchantINN) {
        this.merchantINN = merchantINN;
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
}
