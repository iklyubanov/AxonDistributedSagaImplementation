package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by ivan on 12/20/2015.
 */
@Event
class AddFoundsToMerchantEvent {
    String paymentId
    String transactionId
    Long merchantBankId
    Long merchantCardId
}
