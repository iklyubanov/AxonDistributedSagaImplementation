package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by ivan on 12/15/2015.
 */
@Event
class MerchantBankValidationFailedEvent {
    String transactionId
    String reason

    MerchantBankValidationFailedEvent(String transactionId, String reason) {
        this.transactionId = transactionId
        this.reason = reason
    }
}
