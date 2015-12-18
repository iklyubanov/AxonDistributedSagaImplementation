package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event
/**
 * Created by kliubanov on 17.12.2015.
 */
@Deprecated
@Event
class PaymentNotFoundEvent {
  final String transactionId
  final String paymentId

  PaymentNotFoundEvent(String transactionId, String paymentId) {
    this.transactionId = transactionId
    this.paymentId = paymentId
  }
}
