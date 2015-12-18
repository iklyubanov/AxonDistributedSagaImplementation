package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by ivan on 12/18/2015.
 */
@Event
class WithdrawClientMoneyEvent {
    String paymentId
    String transactionId
    Long issuingBankId
    Long clientCardId
}
