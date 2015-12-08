package ru.iklyubanov.diploma.saga.remote.aggregate

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier

/**
 * Created by kliubanov on 08.12.2015.
 */
class IssuingBankAggregate  extends AbstractAnnotatedAggregateRoot {
  private static final long serialVersionUID = 1299083385130634015L;

  @AggregateIdentifier
  private String bankId;


}
