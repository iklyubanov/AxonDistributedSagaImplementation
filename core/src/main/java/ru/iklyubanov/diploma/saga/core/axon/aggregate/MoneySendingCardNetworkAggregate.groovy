package ru.iklyubanov.diploma.saga.core.axon.aggregate

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import ru.iklyubanov.diploma.saga.gcore.axon.command.PaymentRejectedCommand
import ru.iklyubanov.diploma.saga.gcore.axon.command.SendMoneyByCardNetworkCommand
import ru.iklyubanov.diploma.saga.gcore.axon.event.*

/**
 * агрегат связывающий кластеры
 * Created by kliubanov on 08.12.2015.
 */
class MoneySendingCardNetworkAggregate extends AbstractAnnotatedAggregateRoot {
  private static final long serialVersionUID = 1288083385130634015L;

  @AggregateIdentifier
  String paymentId
  String transactionId
  Long issuingBankId
  Long clientCardId
  Long merchantBankId
  Long merchantCardId

  MoneySendingCardNetworkAggregate(SendMoneyByCardNetworkCommand command) {
    this.paymentId = command.paymentId
    this.transactionId = command.transactionId
    this.issuingBankId = command.issuingBankId
    this.clientCardId = command.clientCardId
    this.merchantBankId = command.merchantBankId
    this.merchantCardId = command.merchantCardId

    apply(new SafePayEvent(paymentId: paymentId, transactionId: transactionId, issuingBankId: issuingBankId,
            clientCardId: clientCardId, merchantBankId: merchantBankId, merchantCardId: merchantCardId))
  }

  void withdrawClientMoney() {
    apply(new WithdrawClientMoneyEvent(paymentId: paymentId, transactionId: transactionId, issuingBankId: issuingBankId,
            clientCardId: clientCardId))
  }

  void paymentRejected(String reason) {
    apply(new PaymentRejectedEvent(paymentId, transactionId, reason))
  }
}
