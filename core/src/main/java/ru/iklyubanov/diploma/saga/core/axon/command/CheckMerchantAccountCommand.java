package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

/**
 * Created by kliubanov on 08.12.2015.
 */
public class CheckMerchantAccountCommand {

    @TargetAggregateIdentifier
    private final String merchantBankId;
    private final TransactionId transactionId;
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

    public CheckMerchantAccountCommand(String merchantBankId, TransactionId transactionId) {
        this.merchantBankId = merchantBankId;
        this.transactionId = transactionId;
    }

    public String getMerchantBankId() {
        return merchantBankId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
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
}
