package ru.iklyubanov.diploma.saga.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.jpa.util.PaymentType;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class CreatePaymentCommand {

    @TargetAggregateIdentifier
    private final String paymentId;
    private final String cliendId;
    private final String bankCardId;
    private final PaymentType paymentType;

    public CreatePaymentCommand(String paymentId, String cliendId, String bankCardId, PaymentType paymentType) {
        this.paymentId = paymentId;
        this.cliendId = cliendId;
        this.bankCardId = bankCardId;
        this.paymentType = paymentType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getCliendId() {
        return cliendId;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}
