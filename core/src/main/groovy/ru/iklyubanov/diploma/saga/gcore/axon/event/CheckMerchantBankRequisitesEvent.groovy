package ru.iklyubanov.diploma.saga.gcore.axon.event

import ru.iklyubanov.diploma.saga.gcore.annotation.Event

/**
 * Created by kliubanov on 14.12.2015.
 */
@Event
class CheckMerchantBankRequisitesEvent {
  String transactionId

  /**
   * БИК банка получателя
   */
  String merchantBankBIK
  /**
   * Номер счета в банке-получателе
   */
  String merchantBankAccount
  /**
   * Получатель (не > 160 симв)
   */
  String merchant
  /**
   * ИНН (не > 160 симв)
   */
  String merchantINN
}
