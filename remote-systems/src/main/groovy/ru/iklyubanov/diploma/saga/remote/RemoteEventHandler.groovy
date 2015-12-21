package ru.iklyubanov.diploma.saga.remote

import groovy.json.JsonOutput
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.annotation.EventHandler
import org.axonframework.repository.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.iklyubanov.diploma.saga.core.axon.aggregate.MoneySendingCardNetworkAggregate
import ru.iklyubanov.diploma.saga.core.axon.aggregate.PaymentProcessorAggregate
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.core.spring.Bank
import ru.iklyubanov.diploma.saga.core.spring.BankCard
import ru.iklyubanov.diploma.saga.core.spring.Payment
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor
import ru.iklyubanov.diploma.saga.gcore.axon.command.BankBikFoundedCommand
import ru.iklyubanov.diploma.saga.gcore.axon.event.*
import ru.iklyubanov.diploma.saga.remote.service.IssuingBankService
import ru.iklyubanov.diploma.saga.remote.service.PaymentProcessorService

/**Step 2: YapStone to Payment Processor

 YapStone sends the transaction details to our payment processor.

 Step 3: Payment Processor to Card Networks to Card Issuing Bank

 Payment details are validated by the payment processor by sending them through the credit or debit card networks
 (Visa, MasterCard, American Express, Discover), which forward them to the card-issuing bank to be authorized.

 * Created by ivan on 12/6/2015.
 */
@Component
class RemoteEventHandler {

    //TODO АГРЕГАТЫ в EVENT HANDLERах НЕ ИСПОЛЬЗУЮТСЯ!!!!!
    @Autowired
    PaymentProcessorService paymentProcessorService
    @Autowired
    IssuingBankService issuingBankService
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    Repository<PaymentProcessorAggregate> repository
    @Autowired
    Repository<MoneySendingCardNetworkAggregate> cardNetworkRepository

    @EventHandler
    public void handle(ProcessPaymentEvent event) {
        //выделяем свободный процессор
        PaymentProcessor freeProcessor = paymentProcessorService.getFreeProcessor()
        freeProcessor.identifier = event.transactionId
        def json = JsonOutput.toJson(event)
        //создаем новый платежна стороне процессора
        Payment payment = paymentProcessorService.createNewPayment(json)
        //определяем бик банка по коду карты плательщика
        payment.issuingBankBIK = paymentProcessorService.findIssuingBankBIK(payment.bankCardCode)
        freeProcessor.payments << payment
        ++freeProcessor.currentTransactionsCount //todo check
        paymentProcessorService.save(freeProcessor)
        //PaymentProcessorAggregate paymentProcessorAggregate = repository.load(event.transactionId)
        //paymentProcessorAggregate.saveBik(payment.issuingBankBIK)
        commandGateway.send(new BankBikFoundedCommand(transactionId: new TransactionId(event.transactionId), issuingBankBIK: payment.issuingBankBIK))
    }

    /** Здесь обрабатываем запрос клиента на перевод средств банком эмитентом
     * todo Если не будет работать загрузка агрегата - переделать как в методе обработки ProcessPaymentEvent
     * */
    @EventHandler
    public void handle(CheckNewPaymentByIssuingBankEvent event) {
        def transactId = event.transactionId
        PaymentProcessorAggregate paymentProcessorAggregate = repository.load(transactId)
        //check client, account, card and current amount
        try {
            Bank bank = issuingBankService.findBank(event.getIssuingBankBIK())
            BankCard card = issuingBankService.checkBankCard(bank, event.code, event.firstName, event.lastName,
                    event.amount, event.currencyType, event.expiredDate, event.ccvCode)
            if (card) {
                paymentProcessorAggregate.succeedIssuingBankValidation(transactId, bank.id, card.id)
            }
        } catch (Exception e) {
            paymentProcessorAggregate.failedIssuingBankValidation(transactId, e.getMessage())
        }
    }

    /** Здесь обрабатываем запрос клиента на перевод средств банком получателя*/
    @EventHandler
    public void handle(CheckMerchantBankRequisitesEvent event) {
        //проверяем реквизиты
        def transactId = event.transactionId
        PaymentProcessorAggregate paymentProcessorAggregate = repository.load(transactId)
        try {
            Bank bank = issuingBankService.findBank(event.getMerchantBankBIK())
            BankCard card = issuingBankService.checkMerchantBankCard(bank, event.merchantBankAccount, event.merchant,
                    event.merchantINN)
            if (card) {
                paymentProcessorAggregate.succeedMerchantBankValidation(transactId, bank.id, card.id)
            }
        } catch (Exception e) {
            paymentProcessorAggregate.failedMerchantBankValidation(transactId, e.getMessage())
        }
    }

    /**Снимаем деньги с клиента*/
    @EventHandler
    public void handle(WithdrawClientMoneyEvent event) {
        def transactionId = event.transactionId
        MoneySendingCardNetworkAggregate cardNetworkAggregate = cardNetworkRepository.load(event.paymentId)
        try {
            PaymentProcessor processor = paymentProcessorService.findProcessor(transactionId)
            Payment payment = paymentProcessorService.findPayment(processor)
            //находим карту клиента и снимаем с нее средства
            def card = issuingBankService.findBankCard(event.clientCardId)
            issuingBankService.withdrawMoneyFromCard(card, payment)
            //с процессором сохраняется и платеж
            paymentProcessorService.save(processor)
            cardNetworkAggregate.successfulWithdrawal()
        } catch (Exception e) {
            cardNetworkAggregate.paymentRejected(e.getMessage())
        }
    }

    @EventHandler
    public void handle(AddFoundsToMerchantEvent event) {
        def transactionId = event.transactionId
        MoneySendingCardNetworkAggregate cardNetworkAggregate = cardNetworkRepository.load(event.paymentId)
        try {
            PaymentProcessor processor = paymentProcessorService.findProcessor(transactionId)
            Payment payment = paymentProcessorService.findPayment(processor)
            //находим карту клиента и снимаем с нее средства
            def card = issuingBankService.findBankCard(event.merchantCardId)
            issuingBankService.addFoundsToCard(card, payment)
            //с процессором сохраняется и платеж
            paymentProcessorService.save(processor)
            cardNetworkAggregate.paymentSuccessful()
        } catch (Exception e) {
            cardNetworkAggregate.paymentRejected(e.getMessage())
        }
    }
}
