package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

/**
 * Created by ivan on 12/17/2015.
 */
@Command
class PaymentRejectedCommand {
    @TargetAggregateIdentifier
    final String paymentId
    final String transactionId
    final String reason

    PaymentRejectedCommand(String paymentId, String transactionId, String reason) {
        this.paymentId = paymentId
        this.transactionId = transactionId
        this.reason = reason
    }
}
