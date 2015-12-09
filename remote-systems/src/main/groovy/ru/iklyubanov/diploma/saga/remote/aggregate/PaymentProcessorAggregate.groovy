package ru.iklyubanov.diploma.saga.remote.aggregate

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.gcore.axon.event.BikAreFoundedEvent

/**
 * Created by kliubanov on 08.12.2015.
 */
class PaymentProcessorAggregate extends AbstractAnnotatedAggregateRoot {
  private static final long serialVersionUID = 1298083385130634015L;

  @AggregateIdentifier
  private String transactId

  private Payment payment

  PaymentProcessorAggregate(String transactId, Payment payment) {
    this.transactId = transactId
    this.payment = payment

    apply(new BikAreFoundedEvent(transactionId: transactId, issuingBankBIK: payment.issuingBankBIK))
  }
}
