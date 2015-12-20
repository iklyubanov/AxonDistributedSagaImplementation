package ru.iklyubanov.diploma.saga.remote.commandhandler

import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.repository.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.iklyubanov.diploma.saga.core.axon.aggregate.MoneySendingCardNetworkAggregate
import ru.iklyubanov.diploma.saga.core.axon.aggregate.PaymentProcessorAggregate
import ru.iklyubanov.diploma.saga.gcore.axon.command.BankBikFoundedCommand
import ru.iklyubanov.diploma.saga.gcore.axon.command.MerchantAddFoundsCommand
import ru.iklyubanov.diploma.saga.gcore.axon.command.PaymentRejectedCommand
import ru.iklyubanov.diploma.saga.remote.command.WithdrawClientMoneyCommand

/**
 * Created by kliubanov on 08.12.2015.
 */
@Component
class RemoteCommandHandler {

  @Autowired
  Repository<PaymentProcessorAggregate> processorRepository

  @Autowired
  Repository<MoneySendingCardNetworkAggregate> cardNetworkRepository

  @CommandHandler
  void handle(BankBikFoundedCommand command) {
    PaymentProcessorAggregate paymentProcessorAggregate = processorRepository.load(command.transactionId.toString())
    paymentProcessorAggregate.saveBik(command.issuingBankBIK)
  }

  @CommandHandler
  void handle(PaymentRejectedCommand command) {
    MoneySendingCardNetworkAggregate cardNetworkAggregate = cardNetworkRepository.load(command.paymentId)
    cardNetworkAggregate.paymentRejected(command.reason)
  }

  @CommandHandler
  void handle(WithdrawClientMoneyCommand command) {
    //todo возможно лучше вынести paymentId в отдельный класс как transactionId
    MoneySendingCardNetworkAggregate cardNetworkAggregate = cardNetworkRepository.load(command.paymentId)
    cardNetworkAggregate.withdrawClientMoney()
  }

  @CommandHandler
  void handle(MerchantAddFoundsCommand command) {
    MoneySendingCardNetworkAggregate cardNetworkAggregate = cardNetworkRepository.load(command.paymentId)
    cardNetworkAggregate.addFoundsToMerchant()
  }

}
