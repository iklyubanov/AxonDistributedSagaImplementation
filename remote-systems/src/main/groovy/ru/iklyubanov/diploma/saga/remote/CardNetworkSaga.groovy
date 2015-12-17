package ru.iklyubanov.diploma.saga.remote

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.saga.annotation.AbstractAnnotatedSaga
import org.axonframework.saga.annotation.SagaEventHandler
import org.axonframework.saga.annotation.StartSaga
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor
import ru.iklyubanov.diploma.saga.gcore.axon.command.PaymentRejectedCommand
import ru.iklyubanov.diploma.saga.gcore.axon.event.SafePayEvent
import ru.iklyubanov.diploma.saga.remote.service.PaymentProcessorService

/**
 * Created by ivan on 12/17/2015.
 */
class CardNetworkSaga extends AbstractAnnotatedSaga {
    private static final long serialVersionUID = 6948996680443725871L;
    private static final Logger logger = LoggerFactory.getLogger(CardNetworkSaga.class);

    private TransactionId transactionId
    private String paymentId

    @Autowired
    private transient CommandGateway commandGateway

    @Autowired
    PaymentProcessorService paymentProcessorService

    @StartSaga
    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(SafePayEvent event) {
        transactionId = new TransactionId(event.transactionId)
        PaymentProcessor processor = paymentProcessorService.findProcessor(transactionId.toString())
        Payment payment
        for (Payment pay : processor.payments) {
            if (pay.identifier.equals(transactionId)) {
                payment = pay
            }
        }
        if (!payment) {
            def reason = "Платеж не найден."
            //todo обработать
            commandGateway.send(new PaymentRejectedCommand(event.paymentId, event.transactionId, reason))
        }
    }
}
