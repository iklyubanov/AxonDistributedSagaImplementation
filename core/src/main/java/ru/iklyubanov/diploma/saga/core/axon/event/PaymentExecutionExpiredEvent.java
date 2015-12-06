package ru.iklyubanov.diploma.saga.core.axon.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentExecutionExpiredEvent {

    @TargetAggregateIdentifier
    private final TransactionId transactionId;

    public PaymentExecutionExpiredEvent(TransactionId transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }
}
