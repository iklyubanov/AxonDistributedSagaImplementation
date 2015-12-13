package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by ivan on 12/13/2015.
 */
@Event
class IssuingBankValidationFailedEvent {
    String transactionId
    String reason

    IssuingBankValidationFailedEvent(String transactionId, String reason) {
        this.transactionId = transactionId
        this.reason = reason
    }
}
