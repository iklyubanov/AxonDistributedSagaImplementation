package ru.iklyubanov.diploma.saga.gcore.axon.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Event;

/**
 * Created by kliubanov on 27.11.2015.
 */
@Event
public class PaymentExecutionExpiredEvent {

    @TargetAggregateIdentifier
    private final TransactionId transactionId

    public PaymentExecutionExpiredEvent(TransactionId transactionId) {
        this.transactionId = transactionId
    }
}
