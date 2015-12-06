package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class SendMoneyByCardNetworkCommand {

    @TargetAggregateIdentifier
    private final String cardNetworkId;
    private final String paymentId;

    public SendMoneyByCardNetworkCommand(String cardNetworkId, String paymentId) {
        this.cardNetworkId = cardNetworkId;
        this.paymentId = paymentId;
    }

    public String getCardNetworkId() {
        return cardNetworkId;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
