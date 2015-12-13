package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

/**
 * Created by kliubanov on 27.11.2015.
 */
@Command
public class CheckNewPaymentByIssuingBankCommand {

    @TargetAggregateIdentifier
    final String bankCardId
    final TransactionId transactionId

    /**
     * Тип карты (PaymentSystemType)
     */
    String paymentSystemType
    /**
     * Код карты
     */
    String code
    /**
     * Имя держателя карты
     */
    String firstName
    /**
     * Фамилия держателя карты
     */
    String lastName
    /**
     * Дата окончания срока действия карты
     */
    String expiredDate
    /**
     * Код безопасности (CVC2, CVV2, CID, CSC)
     */
    String ccvCode

    public CheckNewPaymentByIssuingBankCommand(String bankCardId, TransactionId transactionId) {
        this.bankCardId = bankCardId
        this.transactionId = transactionId
    }
}
