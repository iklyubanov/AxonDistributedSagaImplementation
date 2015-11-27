package ru.iklyubanov.diploma.saga.axon.event;

import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentEventHandler {

    @EventHandler
    public void handle(CreatePaymentEvent event) {
        System.out.println("We should do something with " + event.getPaymentId());
    }

}
