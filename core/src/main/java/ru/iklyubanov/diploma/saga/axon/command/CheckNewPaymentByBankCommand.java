package ru.iklyubanov.diploma.saga.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class CheckNewPaymentByBankCommand {

    @TargetAggregateIdentifier
    private final String bankCardId;
    private final String paymentId;

    public CheckNewPaymentByBankCommand(String bankCardId, String paymentId) {
        this.bankCardId = bankCardId;
        this.paymentId = paymentId;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
