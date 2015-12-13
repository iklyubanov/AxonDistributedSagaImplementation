package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by ivan on 12/13/2015.
 */
@Event
class IssuingBankValidationSucceedEvent {
    String transactionId
    Long bankId
    Long bankCardId
}
