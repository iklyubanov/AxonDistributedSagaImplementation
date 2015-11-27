package ru.iklyubanov.diploma.saga.axon.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentExecutionExpiredEvent {

    @TargetAggregateIdentifier
    private final String paymentId;

    public PaymentExecutionExpiredEvent(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
