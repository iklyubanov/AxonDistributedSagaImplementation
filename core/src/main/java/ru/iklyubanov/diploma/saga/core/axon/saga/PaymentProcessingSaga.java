package ru.iklyubanov.diploma.saga.core.axon.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iklyubanov.diploma.saga.gcore.axon.command.CheckMerchantAccountCommand;
import ru.iklyubanov.diploma.saga.gcore.axon.command.CheckNewPaymentByIssuingBankCommand;
import ru.iklyubanov.diploma.saga.gcore.axon.command.ProcessPaymentByProcessorCommand;
import ru.iklyubanov.diploma.saga.gcore.axon.event.BankBikFoundedEvent;
import ru.iklyubanov.diploma.saga.gcore.axon.event.PaymentExecutionExpiredEvent;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;
import ru.iklyubanov.diploma.saga.gcore.axon.event.CreatePaymentEvent;

/**
 * Сначала проверяем счет клиента в банке клиента.
 * Если все ок, проверяем счет получателя в банке получателя.
 * Если все ок, передаем процессору средства для платежа.
 * Процессор иницализирует перевод средств от клиента к получателю.
 * Асинхронно происходит списывание со счета клиента в банке клиента и зачисление средств на счет получателя
 * с помощью запущенной системы платежей.
 * Если там и там успешно списалось  - уведомляем клиента.
 *
 * TODO may be migrate to mongo?
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentProcessingSaga extends AbstractAnnotatedSaga {
    private static final long serialVersionUID = 5948996680443725871L;
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingSaga.class);

    private TransactionId transactionId;
    private boolean isIssuingBankChecked = false;
    private boolean isMerchantBankChecked = false;
    private boolean isMoneyTransfered = false;

    @Autowired
    private transient CommandGateway commandGateway;
    /*todo or 'private transient CommandBus commandBus;'*/

    @Autowired
    private transient EventScheduler eventScheduler;


    /*TODO Нужно как то в саге хранить информацию о платеже*/
    @StartSaga
    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(CreatePaymentEvent event) {
        transactionId = event.getTransactionId();
        logger.info("A new payment '{}' created.", transactionId);

        // send the commands
        // ...getting processor..
        ProcessPaymentByProcessorCommand processorCommand = createProcessPaymentByProcessorCommand(event);
        commandGateway.send(processorCommand);

        CheckMerchantAccountCommand checkMerchantAccountCommand = createCheckMerchantAccountCommand(event);
        //send to merchant bank
        commandGateway.send(checkMerchantAccountCommand);

        //SendMoneyByCardNetworkCommand sendMoneyCommand = new SendMoneyByCardNetworkCommand();

        PaymentExecutionExpiredEvent expiredEvent = new PaymentExecutionExpiredEvent(event.getTransactionId());
        eventScheduler.schedule(Duration.standardMinutes(event.getTimeout()), expiredEvent);
    }

    @SagaEventHandler(associationProperty = "transactionId")//TODO check that it is a transactionId, make test
    public void handle(BankBikFoundedEvent bikFoundedEvent) {
        // send the commands
        //создадим TargetAggregateIdentifier таким образом
        String issuingBankAggregateId =  bikFoundedEvent.getIssuingBankBIK();
        associateWith("bankCardId", issuingBankAggregateId);
        //send to card-issuing bank
        CheckNewPaymentByIssuingBankCommand checkByBankCommand = new CheckNewPaymentByIssuingBankCommand(issuingBankAggregateId, transactionId);
        commandGateway.send(checkByBankCommand);
    }

    private ProcessPaymentByProcessorCommand createProcessPaymentByProcessorCommand(CreatePaymentEvent event) {
        ProcessPaymentByProcessorCommand processorCommand = new ProcessPaymentByProcessorCommand(event.getTransactionId().toString());
        associateWith("transactionId", event.getTransactionId().toString());
        processorCommand.setAmount(event.getAmount());
        processorCommand.setCurrencyType(event.getCurrencyType());
        processorCommand.setPaymentType(event.getPaymentType());
        processorCommand.setCode(event.getCode());
        processorCommand.setFirstName(event.getFirstName());
        processorCommand.setLastName(event.getLastName());
        processorCommand.setCcvCode(event.getCcvCode());
        processorCommand.setExpiredDate(event.getExpiredDate());
        return processorCommand;
    }

    private CheckMerchantAccountCommand createCheckMerchantAccountCommand(CreatePaymentEvent event) {
        String merchantBankAggregateId = event.getMerchantBankBIK();
        // associate the Saga with these values, before sending the commands
        associateWith("merchantBankId", merchantBankAggregateId);
        CheckMerchantAccountCommand checkMerchantAccountCommand = new CheckMerchantAccountCommand(merchantBankAggregateId, event.getTransactionId());
        checkMerchantAccountCommand.setMerchant(event.getMerchant());
        checkMerchantAccountCommand.setMerchantBankAccount(event.getMerchantBankAccount());
        checkMerchantAccountCommand.setMerchantINN(event.getMerchantINN());
        return checkMerchantAccountCommand;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionId transactionId) {
        this.transactionId = transactionId;
    }
}
