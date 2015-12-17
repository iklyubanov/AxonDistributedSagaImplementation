package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by ivan on 12/17/2015.
 */
@Event
class SafePayEvent {
    String paymentId
    String transactionId
    Long issuingBankId
    Long clientCardId
    Long merchantBankId
    Long merchantCardId
}
