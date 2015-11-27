package ru.iklyubanov.diploma.saga.axon.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iklyubanov.diploma.saga.axon.command.ProcessPaymentByProcessorCommand;
import ru.iklyubanov.diploma.saga.axon.event.CreatePaymentEvent;
import ru.iklyubanov.diploma.saga.axon.event.PaymentExecutionExpiredEvent;

import java.util.UUID;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentProcessingSaga extends AbstractAnnotatedSaga {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient EventScheduler eventScheduler;

    @StartSaga
    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(CreatePaymentEvent event) {
        logger.info("A new payment '{}' created.", event.getPaymentId());
        //TODO here we need to choose existing PaymentProcessor by card system type (may be inject by spring config)
        // ...getting processor.. Now it just emulate processor
        String procId = UUID.randomUUID().toString();
        ProcessPaymentByProcessorCommand processorCommand = new ProcessPaymentByProcessorCommand(procId, event.getPaymentId(), null);//TODO set transaction details
        commandGateway.send(processorCommand);

        PaymentExecutionExpiredEvent expiredEvent = new PaymentExecutionExpiredEvent(event.getPaymentId());
        eventScheduler.schedule(Duration.standardMinutes(event.getTimeout()), expiredEvent);
    }


}
