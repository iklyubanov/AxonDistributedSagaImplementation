package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;
import ru.iklyubanov.diploma.saga.core.axon.PaymentAggregate;
import ru.iklyubanov.diploma.saga.gcore.axon.command.CreatePaymentCommand;

/**
 * Класс обрабатывающий команды связанные с платежами
 * Created by ivan on 12/5/2015.
 */
@Component
public class PaymentCommandHandler {

    private Repository<PaymentAggregate> repository;

    @CommandHandler
    public void handle(CreatePaymentCommand command) {
        /*создаем агрегат платежа для хранения состаяния платежа*/
        PaymentAggregate paymentAggregate = new PaymentAggregate.Builder(command.getTransactionId())
                .addPaymentSystemType(command.getPaymentSystemType()).addAmount(command.getAmount())
                .addCurrencyType(command.getCurrencyType()).addCode(command.getCode()).addFirstName(command.getFirstName())
                .addLastName(command.getLastName()).addExpiredDate(command.getExpiredDate()).addCcvCode(command.getCcvCode())
                .addPaymentType(command.getPaymentType()).addMerchantBankBIK(command.getMerchantBankBIK())
                .addMerchantBankAccount(command.getMerchantBankAccount()).addMerchant(command.getMerchant())
                .addMerchantINN(command.getMerchantINN()).build();

        /*и сохраняем его в репозиторий*/
        repository.add(paymentAggregate);
        /*apply(new CreatePaymentEvent(command.getPaymentId(), command.getCliendId(),
                command.getBankCardId(), command.getPaymentType(), 2));*/
    }

    public void setRepository(Repository<PaymentAggregate> repository) {
        this.repository = repository;
    }
}
