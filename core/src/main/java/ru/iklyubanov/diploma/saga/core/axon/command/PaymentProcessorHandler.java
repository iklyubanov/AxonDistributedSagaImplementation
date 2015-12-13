package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iklyubanov.diploma.saga.core.axon.aggregate.PaymentProcessorAggregate;
import ru.iklyubanov.diploma.saga.gcore.axon.command.CheckNewPaymentByIssuingBankCommand;
import ru.iklyubanov.diploma.saga.gcore.axon.command.ProcessPaymentByProcessorCommand;
import ru.iklyubanov.diploma.saga.gcore.axon.command.SendMoneyByCardNetworkCommand;

/**
 * Created by kliubanov on 27.11.2015.
 */
@Component
public class PaymentProcessorHandler {

    @Autowired
    Repository<PaymentProcessorAggregate> repository;

    @CommandHandler
    public void handle(ProcessPaymentByProcessorCommand command) {
        PaymentProcessorAggregate processorAggregate = new PaymentProcessorAggregate(command.getTransactionId(), command);
        repository.add(processorAggregate);
    }

    @CommandHandler
    public void handle(CheckNewPaymentByIssuingBankCommand command) {
        PaymentProcessorAggregate processorAggregate = repository.load(command.getTransactionId().toString());
        processorAggregate.checkNewPaymentByIssuingBank();

    }

    @CommandHandler
    public void handle(SendMoneyByCardNetworkCommand command) {

    }
}
