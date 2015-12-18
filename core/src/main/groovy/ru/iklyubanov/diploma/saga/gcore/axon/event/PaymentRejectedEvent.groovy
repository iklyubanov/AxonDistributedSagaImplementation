package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * завершает обе саги
 * Created by ivan on 12/18/2015.
 */
@Event
class PaymentRejectedEvent {
    final String paymentId
    final String transactionId
    final String reason

    PaymentRejectedEvent(String paymentId, String transactionId, String reason) {
        this.paymentId = paymentId
        this.transactionId = transactionId
        this.reason = reason
    }
}
