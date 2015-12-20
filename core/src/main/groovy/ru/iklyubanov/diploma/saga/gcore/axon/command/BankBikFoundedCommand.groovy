package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

import javax.validation.constraints.NotNull

/**
 * Created by kliubanov on 27.11.2015.
 */
@Command
public class BankBikFoundedCommand {

    @NotNull
    @TargetAggregateIdentifier
    TransactionId transactionId
    String issuingBankBIK
}
