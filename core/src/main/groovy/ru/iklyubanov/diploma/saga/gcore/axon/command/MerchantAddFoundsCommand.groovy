package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

/**
 * Created by ivan on 12/20/2015.
 */
@Command
class MerchantAddFoundsCommand {
    @TargetAggregateIdentifier
    final String paymentId
    final TransactionId transactionId

    MerchantAddFoundsCommand(String paymentId, TransactionId transactionId) {
        this.paymentId = paymentId
        this.transactionId = transactionId
    }
}
