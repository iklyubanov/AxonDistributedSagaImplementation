package ru.iklyubanov.diploma.saga.remote.commandhandler

import org.axonframework.repository.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.iklyubanov.diploma.saga.gcore.axon.command.CheckNewPaymentByIssuingBankCommand
import ru.iklyubanov.diploma.saga.core.axon.aggregate.IssuingBankAggregate

/**
 * Created by kliubanov on 08.12.2015.
 */
@Component
class RemoteIssuingBankCommandHandler {

  @Autowired
  Repository<IssuingBankAggregate> issuingBankRepository

  void handle(CheckNewPaymentByIssuingBankCommand issuingBankCommand) {
    IssuingBankAggregate issuingBankAggregate = issuingBankRepository.load(issuingBankCommand.bankCardId)
    issuingBankAggregate
  }

}
