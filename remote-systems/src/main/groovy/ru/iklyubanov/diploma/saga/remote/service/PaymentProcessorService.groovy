package ru.iklyubanov.diploma.saga.remote.service

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor
import ru.iklyubanov.diploma.saga.core.spring.util.MonetaryValue
import ru.iklyubanov.diploma.saga.core.spring.util.PaymentState
import ru.iklyubanov.diploma.saga.core.spring.util.PaymentType
import ru.iklyubanov.diploma.saga.remote.repository.PaymentProcessorRepository

/**
 * Created by ivan on 12/6/2015.
 */
@Service
@Transactional
class PaymentProcessorService {

    @Autowired
    PaymentProcessorRepository repository

    @Transactional(readOnly = true)
    PaymentProcessor getFreeProcessor() {
        def freeProcessor = repository.findFreeProcessor()
        if(!freeProcessor) {
            freeProcessor = new PaymentProcessor()
            def uuid = UUID.randomUUID().toString()
            freeProcessor.name = "Processor_$uuid"
        }
        freeProcessor
    }

    PaymentProcessor save(PaymentProcessor paymentProcessor) {
        repository.save(paymentProcessor)
    }

    Payment createNewPayment(def jsonProcessCommand) {
        def payment = new Payment()
        payment.paymentType = PaymentType.valueOf(jsonProcessCommand.paymentType)
        payment.paymentState = PaymentState.NEW
        payment.bankCardCode = jsonProcessCommand.code
        def monetaryVal = new MonetaryValue(jsonProcessCommand.currencyType, jsonProcessCommand.amount)
        payment.paymentAmount = monetaryVal
        payment.identifier = jsonProcessCommand.transactionId.toString()
        payment
    }

  /**Эмулируем определитель банка по первым 6 символам кода карты
   * TODO если останеться время можно вытаскивать бики из базы*/
    String findIssuingBankBIK(String cardCode) {
      /*BIN платежной карты определяется по первым 6 цифрам ее номера на лицевой стороне*/
      def bin = cardCode.substring(0, 6)
      /*По первой цифре можно определить, в какой системе обслуживается карта. Например:
        4 — VISA
        5 — MasterCard
        3 — American Express*/
      def rand = new Random()
      bin + StringUtils.leftPad('' + rand.nextInt(999999), 6, "0")
    }
}
