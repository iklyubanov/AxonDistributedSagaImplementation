package ru.iklyubanov.diploma.saga.core.axon.event;

import org.axonframework.eventhandling.annotation.EventHandler;
import ru.iklyubanov.diploma.saga.gcore.axon.event.CreatePaymentEvent;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentEventHandler {

    @EventHandler
    public void handle(CreatePaymentEvent event) {
        System.out.println("We should do something with " + event.getTransactionId());
    }

}
