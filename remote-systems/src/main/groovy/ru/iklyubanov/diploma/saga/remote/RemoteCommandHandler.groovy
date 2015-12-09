package ru.iklyubanov.diploma.saga.remote
import groovy.json.JsonOutput
import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.repository.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.iklyubanov.diploma.saga.core.axon.command.ProcessPaymentByProcessorCommand
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor
import ru.iklyubanov.diploma.saga.remote.aggregate.PaymentProcessorAggregate
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
    @Autowired
    Repository<PaymentProcessorAggregate> repository

    @CommandHandler
    public void handle(ProcessPaymentByProcessorCommand processPaymentByProcessorCommand) {
        //выделяем свободный процессор
        PaymentProcessor freeProcessor = paymentProcessorService.getFreeProcessor()
        def json = JsonOutput.toJson(processPaymentByProcessorCommand)
        //создаем новый платежна стороне процессора
        Payment payment = paymentProcessorService.createNewPayment(json)
        //определяем бик банка по коду карты плательщика
        payment.issuingBankBIK = paymentProcessorService.findIssuingBankBIK(payment.bankCardCode)
        PaymentProcessorAggregate processorAggregate = new PaymentProcessorAggregate(processPaymentByProcessorCommand.transactionId, payment)
        repository.add(processorAggregate)
        freeProcessor.payments << payment
        ++freeProcessor.currentTransactionsCount //todo check
        paymentProcessorService.save(freeProcessor)
    }

}
