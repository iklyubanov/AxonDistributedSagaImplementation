package ru.iklyubanov.diploma.saga.remote.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

/**
 * Created by ivan on 12/18/2015.
 */
@Command
class WithdrawClientMoneyCommand {
    @TargetAggregateIdentifier
    final String paymentId
    final String transactionId

}
