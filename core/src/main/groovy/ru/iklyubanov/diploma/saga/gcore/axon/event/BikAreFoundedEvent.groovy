package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event
/**
 * Created by kliubanov on 08.12.2015.
 */
@Event
class BikAreFoundedEvent {
  String transactionId
  String issuingBankBIK
}
