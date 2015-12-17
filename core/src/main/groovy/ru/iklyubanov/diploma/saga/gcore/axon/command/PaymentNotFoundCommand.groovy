package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

/**
 * Created by ivan on 12/17/2015.
 */
@Command
class PaymentNotFoundCommand {
    @TargetAggregateIdentifier
    final TransactionId transactionId
    final String paymentId

  PaymentNotFoundCommand(TransactionId transactionId, String paymentId) {
        this.paymentId = paymentId
        this.transactionId = transactionId
    }
}
