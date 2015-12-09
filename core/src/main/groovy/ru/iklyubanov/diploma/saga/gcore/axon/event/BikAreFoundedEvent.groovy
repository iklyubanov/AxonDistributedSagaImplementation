package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event
/**
 * Created by kliubanov on 08.12.2015.
 */
@Event
class BikAreFoundedEvent {
  String transactionId
  String issuingBankBIK
  /**Код карты*/
  String code
  /**
   * Имя держателя карты
   */
  String firstName
  /**
   * Фамилия держателя карты
   */
  String lastName
  /**Дата окончания срока действия карты*/
  String expiredDate
  /** Код безопасности (CVC2, CVV2, CID, CSC)*/
  String ccvCode
}
