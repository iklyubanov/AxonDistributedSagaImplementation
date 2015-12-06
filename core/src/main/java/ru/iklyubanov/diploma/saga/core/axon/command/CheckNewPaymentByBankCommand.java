package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class CheckNewPaymentByBankCommand {

    @TargetAggregateIdentifier
    private final String bankCardId;
    private final TransactionId transactionId;

    public CheckNewPaymentByBankCommand(String bankCardId, TransactionId transactionId) {
        this.bankCardId = bankCardId;
        this.transactionId = transactionId;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }
}
