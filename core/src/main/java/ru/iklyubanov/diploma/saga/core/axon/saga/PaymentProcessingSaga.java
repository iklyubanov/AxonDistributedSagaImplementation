package ru.iklyubanov.diploma.saga.core.axon.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iklyubanov.diploma.saga.core.axon.command.CheckNewPaymentByBankCommand;
import ru.iklyubanov.diploma.saga.core.axon.command.ProcessPaymentByProcessorCommand;
import ru.iklyubanov.diploma.saga.gcore.axon.event.CreatePaymentEvent;
import ru.iklyubanov.diploma.saga.core.axon.event.PaymentExecutionExpiredEvent;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

import java.util.UUID;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentProcessingSaga extends AbstractAnnotatedSaga {
    private static final long serialVersionUID = 5948996680443725871L;
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingSaga.class);

    private TransactionId transactionId;

    @Autowired
    private transient CommandGateway commandGateway;
    /*todo or 'private transient CommandBus commandBus;'*/

    @Autowired
    private transient EventScheduler eventScheduler;

    @StartSaga/*доработать*/
    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(CreatePaymentEvent event) {
        transactionId = event.getTransactionId();
        logger.info("A new payment '{}' created.", transactionId);
        //TODO here we need to choose existing PaymentProcessor by card system type (may be inject by spring config)
        // ...getting processor.. Now it just emulate processor
        String procId = UUID.randomUUID().toString();
        ProcessPaymentByProcessorCommand processorCommand = new ProcessPaymentByProcessorCommand(procId, event.getTransactionId(), null);//TODO set transaction details
        commandGateway.send(processorCommand);
        //TODO может вынести остальные команды в отдельные event handler'ы ?
        //send to card-issuing bank
        CheckNewPaymentByBankCommand checkByBankCommand = new CheckNewPaymentByBankCommand(event.getCode(), event.getTransactionId());
        commandGateway.send(checkByBankCommand);

        //SendMoneyByCardNetworkCommand sendMoneyCommand = new SendMoneyByCardNetworkCommand();

        PaymentExecutionExpiredEvent expiredEvent = new PaymentExecutionExpiredEvent(event.getTransactionId());
        eventScheduler.schedule(Duration.standardMinutes(event.getTimeout()), expiredEvent);
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionId transactionId) {
        this.transactionId = transactionId;
    }
}
