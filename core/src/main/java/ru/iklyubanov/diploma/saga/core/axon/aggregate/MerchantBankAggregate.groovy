package ru.iklyubanov.diploma.saga.core.axon.aggregate

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot

/**
 * //todo сюда возможно стоит перенести информацию о получателе из PaymentProcessorAggregate
 * Created by kliubanov on 08.12.2015.
 */
class MerchantBankAggregate extends AbstractAnnotatedAggregateRoot {
  private static final long serialVersionUID = 1299083385130634016L;
}
