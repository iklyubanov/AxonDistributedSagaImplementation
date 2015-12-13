package ru.iklyubanov.diploma.saga.core.axon.aggregate

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import ru.iklyubanov.diploma.saga.gcore.axon.command.ProcessPaymentByProcessorCommand
import ru.iklyubanov.diploma.saga.gcore.axon.event.BankBikFoundedEvent
import ru.iklyubanov.diploma.saga.gcore.axon.event.ProcessPaymentEvent

/**
 * агрегат связывающий кластеры
 * Created by kliubanov on 08.12.2015.
 */
class PaymentProcessorAggregate extends AbstractAnnotatedAggregateRoot {
  private static final long serialVersionUID = 1298083385130634015L;

  @AggregateIdentifier
  String transactId
  /**
   * К оплате
   */
  BigDecimal amount;
  /**
   * Валюта
   */
  String currencyType;
  /**
   * Тип платежа
   */
  String paymentType;
  /**
   * Код карты
   */
  String code;
  /**
   * Имя держателя карты
   */
  String firstName;
  /**
   * Фамилия держателя карты
   */
  String lastName;
  /**Дата окончания срока действия карты*/
  String expiredDate;
  /** Код безопасности (CVC2, CVV2, CID, CSC)*/
  String ccvCode;
  /*Bank bik*/
  String issuingBankBIK;

  PaymentProcessorAggregate(String transactId, ProcessPaymentByProcessorCommand processPaymentByProcessorCommand) {
    this.transactId = transactId
    this.amount = processPaymentByProcessorCommand.amount
    this.currencyType = processPaymentByProcessorCommand.currencyType
    this.paymentType = processPaymentByProcessorCommand.paymentType
    this.code = processPaymentByProcessorCommand.code
    this.firstName = processPaymentByProcessorCommand.firstName
    this.lastName = processPaymentByProcessorCommand.lastName
    this.expiredDate = processPaymentByProcessorCommand.expiredDate
    this.ccvCode = processPaymentByProcessorCommand.ccvCode

    apply(new ProcessPaymentEvent(transactionId: transactId, amount: amount,
            currencyType: currencyType, paymentType: paymentType, code: code, firstName: firstName,
            lastName: lastName, expiredDate: expiredDate, ccvCode: ccvCode))
  }

  void saveBik(def bik) {
    issuingBankBIK = bik
    apply(new BankBikFoundedEvent(transactionId: transactId, issuingBankBIK: issuingBankBIK))
  }
}
