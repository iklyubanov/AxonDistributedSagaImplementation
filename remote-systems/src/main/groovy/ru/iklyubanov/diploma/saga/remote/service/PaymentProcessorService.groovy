package ru.iklyubanov.diploma.saga.remote.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor
import ru.iklyubanov.diploma.saga.core.spring.util.MonetaryValue
import ru.iklyubanov.diploma.saga.core.spring.util.PaymentState
import ru.iklyubanov.diploma.saga.core.spring.util.PaymentType
import ru.iklyubanov.diploma.saga.remote.repository.PaymentProcessorRepository

import javax.transaction.Transactional
/**
 * Created by ivan on 12/6/2015.
 */
@Service
@Transactional
class PaymentProcessorService {

    @Autowired
    PaymentProcessorRepository repository

    PaymentProcessor getFreeProcessor() {
        def freeProcessor = repository.findFreeProcessor()
        if(!freeProcessor) {
            freeProcessor = new PaymentProcessor()
            def uuid = UUID.randomUUID().toString()
            freeProcessor.name = "Processor_$uuid"
            freeProcessor = repository.save(freeProcessor)
        }
        freeProcessor
    }

    def save(PaymentProcessor paymentProcessor) {
        repository.save(paymentProcessor)
    }

    Payment createNewPayment(def jsonProcessCommand) {
        def payment = new Payment()
        payment.paymentType = PaymentType.valueOf(jsonProcessCommand.paymentType)
        payment.paymentState = PaymentState.NEW
        def monetaryVal = new MonetaryValue(jsonProcessCommand.currencyType, jsonProcessCommand.amount)
        payment.paymentAmount = monetaryVal
        payment.identifier = jsonProcessCommand.transactionId.toString()
        payment
    }

}