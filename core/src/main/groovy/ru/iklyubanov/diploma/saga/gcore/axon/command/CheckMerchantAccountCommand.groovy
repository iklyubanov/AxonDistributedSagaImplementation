package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

/**
 * Created by kliubanov on 08.12.2015.
 */
@Command
public class CheckMerchantAccountCommand {

    @TargetAggregateIdentifier
    final TransactionId transactionId
    //final String merchantBankId

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

    public CheckMerchantAccountCommand( TransactionId transactionId) {
        this.transactionId = transactionId
    }
}
