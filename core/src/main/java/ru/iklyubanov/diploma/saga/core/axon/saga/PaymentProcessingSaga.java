package ru.iklyubanov.diploma.saga.core.axon.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.IdentifierFactory;
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
import ru.iklyubanov.diploma.saga.gcore.axon.command.SendMoneyByCardNetworkCommand;
import ru.iklyubanov.diploma.saga.gcore.axon.event.*;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

/**TODO МОЖЕТ ПЕРЕТАЩИТЬ САГУ в MAIN SERVER
 * Сначала проверяем счет клиента в банке клиента.
 * Если все ок, проверяем счет получателя в банке получателя.
 * Если все ок, передаем процессору средства для платежа.
 * Процессор иницализирует перевод средств от клиента к получателю.
 * Асинхронно происходит списывание со счета клиента в банке клиента и зачисление средств на счет получателя
 * с помощью запущенной системы платежей.
 * Если там и там успешно списалось  - уведомляем клиента.
 *
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentProcessingSaga extends AbstractAnnotatedSaga {
    private static final long serialVersionUID = 5948996680443725871L;
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingSaga.class);

    private TransactionId transactionId;
    private boolean isIssuingBankChecked = false;
    private boolean isMerchantBankChecked = false;
    private boolean isMoneyTransfered = false;
    private Long issuingBankId;
    private Long clientCardId;
    private Long merchantBankId;
    private Long merchantCardId;
    private String paymentId;

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

    /** Получили бик клиента-отправителя, отправляем в банк клиента запрос на проверку*/
    @SagaEventHandler(associationProperty = "transactionId")//check that it is a transactionId, make test
    public void handle(BankBikFoundedEvent bikFoundedEvent) {
        // send the commands
        //создадим TargetAggregateIdentifier таким образом
        //todo нужно ли это?
        String issuingBankAggregateId =  bikFoundedEvent.getIssuingBankBIK();
        associateWith("bankCardId", issuingBankAggregateId);
        //send to card-issuing bank
        CheckNewPaymentByIssuingBankCommand checkByBankCommand = new CheckNewPaymentByIssuingBankCommand(issuingBankAggregateId, transactionId);
        commandGateway.send(checkByBankCommand);
    }

    /** Платеж был отклонен банком клиента*/
    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(IssuingBankValidationFailedEvent event) {
        logger.error("Платеж был отклонен банком клиента по причине: " + event.getReason());
        end();
    }

    /** Платеж был одобрен банком клиента*/
    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(IssuingBankValidationSucceedEvent event) {
        logger.info("Платеж был одобрен банком клиента");
        isIssuingBankChecked = true;
        issuingBankId = event.getBankId();
        clientCardId = event.getBankCardId();
        if(isMerchantBankChecked) {
            //todo start money recieving
            sendMoneyByCardNetwork();
        }
    }

    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(MerchantBankValidationFailedEvent event) {
        logger.error("Платеж был отклонен банком получателя по причине: " + event.getReason());
        end();
    }

    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(MerchantBankValidationSucceedEvent event) {
        logger.info("Платеж был одобрен банком получателя");
        isMerchantBankChecked = true;
        merchantBankId = event.getBankId();
        merchantCardId = event.getBankCardId();
        if(isIssuingBankChecked) {
            //start money recieving
            sendMoneyByCardNetwork();
        }
    }

    @SagaEventHandler(associationProperty = "transactionId")
    public void handle(PaymentNotFoundEvent event) {
        logger.error("Платеж " + event.getPaymentId() + " не был найден сетью банковских карт.");
        end();
    }

    private void sendMoneyByCardNetwork() {
        paymentId = IdentifierFactory.getInstance().generateIdentifier();
        SendMoneyByCardNetworkCommand command = new SendMoneyByCardNetworkCommand(paymentId, transactionId.toString());
        command.setClientCardId(clientCardId);
        command.setIssuingBankId(issuingBankId);
        command.setMerchantBankId(merchantBankId);
        command.setMerchantCardId(merchantCardId);
        associateWith("paymentId", paymentId);
        commandGateway.send(command);
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
        // associate the Saga with these values, before sending the commands
        CheckMerchantAccountCommand checkMerchantAccountCommand = new CheckMerchantAccountCommand(event.getTransactionId());
        checkMerchantAccountCommand.setMerchantBankBIK(event.getMerchantBankBIK());
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
