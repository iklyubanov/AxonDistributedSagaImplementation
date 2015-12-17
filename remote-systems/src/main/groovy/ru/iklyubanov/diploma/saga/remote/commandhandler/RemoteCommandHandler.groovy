package ru.iklyubanov.diploma.saga.remote.commandhandler

import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.repository.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.iklyubanov.diploma.saga.core.axon.aggregate.PaymentProcessorAggregate
import ru.iklyubanov.diploma.saga.gcore.axon.command.PaymentNotFoundCommand

/**
 * Created by kliubanov on 08.12.2015.
 */
@Component
class RemoteCommandHandler {

  @Autowired
  Repository<PaymentProcessorAggregate> repository

  @CommandHandler
  void handle(PaymentNotFoundCommand command) {
    PaymentProcessorAggregate processorAggregate = repository.load(command.transactionId.toString())
    processorAggregate.paymentNotFound(command.transactionId.toString(), command.paymentId, command.reason)
  }

}
