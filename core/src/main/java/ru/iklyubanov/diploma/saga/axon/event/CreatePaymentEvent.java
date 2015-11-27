package ru.iklyubanov.diploma.saga.axon.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.jpa.util.PaymentType;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class CreatePaymentEvent {

    @TargetAggregateIdentifier
    private final String paymentId;
    private final String cliendId;
    private final String bankCardId;
    private final PaymentType paymentType;
    private final int timeout;

    public CreatePaymentEvent(String paymentId, String cliendId, String bankCardId, PaymentType paymentType, int timeout) {
        this.paymentId = paymentId;
        this.cliendId = cliendId;
        this.bankCardId = bankCardId;
        this.paymentType = paymentType;
        this.timeout = timeout;
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

    public int getTimeout() {
        return timeout;
    }
}
