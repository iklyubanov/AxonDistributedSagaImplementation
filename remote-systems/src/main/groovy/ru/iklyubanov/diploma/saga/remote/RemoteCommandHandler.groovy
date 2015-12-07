package ru.iklyubanov.diploma.saga.remote

import groovy.json.JsonOutput
import org.axonframework.commandhandling.annotation.CommandHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.iklyubanov.diploma.saga.core.axon.command.CheckNewPaymentByBankCommand
import ru.iklyubanov.diploma.saga.core.axon.command.ProcessPaymentByProcessorCommand
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor
import ru.iklyubanov.diploma.saga.remote.service.PaymentProcessorService

/**Step 2: YapStone to Payment Processor

 YapStone sends the transaction details to our payment processor.

 Step 3: Payment Processor to Card Networks to Card Issuing Bank

 Payment details are validated by the payment processor by sending them through the credit or debit card networks
 (Visa, MasterCard, American Express, Discover), which forward them to the card-issuing bank to be authorized.

 * Created by ivan on 12/6/2015.
 */
@Component
class RemoteCommandHandler {

    @Autowired
    PaymentProcessorService paymentProcessorService
    /*TODO для банка нужно сделать отдельный класс*/

    @CommandHandler
    public void handle(ProcessPaymentByProcessorCommand processPaymentByProcessorCommand) {
        PaymentProcessor freeProcessor = paymentProcessorService.getFreeProcessor()
        def json = JsonOutput.toJson(processPaymentByProcessorCommand)
        Payment payment = paymentProcessorService.createNewPayment(json)
        freeProcessor.payments << payment
        ++freeProcessor.currentTransactionsCount //todo check
        paymentProcessorService.save(freeProcessor)
    }

    @CommandHandler
    public void handle(CheckNewPaymentByBankCommand checkNewPaymentByBankCommand) {

    }



}
