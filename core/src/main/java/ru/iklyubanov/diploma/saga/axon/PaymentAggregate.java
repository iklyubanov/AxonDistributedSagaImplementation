package ru.iklyubanov.diploma.saga.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import ru.iklyubanov.diploma.saga.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.axon.event.CreatePaymentEvent;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentAggregate extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String id;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(CreatePaymentCommand command) {
        apply(new CreatePaymentEvent(command.getPaymentId(), command.getCliendId(),
                command.getBankCardId(), command.getPaymentType(), 2));
    }

    @EventHandler
    public void on(CreatePaymentEvent event) {
        this.id = event.getPaymentId();
    }
}
