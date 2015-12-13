package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

/**
 * Created by kliubanov on 27.11.2015.
 */
@Command
public class SendMoneyByCardNetworkCommand {

    @TargetAggregateIdentifier
    private final String cardNetworkId
    private final String paymentId

    public SendMoneyByCardNetworkCommand(String cardNetworkId, String paymentId) {
        this.cardNetworkId = cardNetworkId
        this.paymentId = paymentId
    }
}
