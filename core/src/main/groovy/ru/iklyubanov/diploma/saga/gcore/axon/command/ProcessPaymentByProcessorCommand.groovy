package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

import javax.validation.constraints.NotNull
import java.math.BigDecimal

/**
 Этой комадной следует передавать информацию о плательщике

 * Created by kliubanov on 27.11.2015.
 */
@Command
public class ProcessPaymentByProcessorCommand {

    @TargetAggregateIdentifier
    final String transactionId

    /**
     * К оплате
     */
    BigDecimal amount
    /**
     * Валюта
     */
    String currencyType

    /**
     * Тип платежа
     */
    String paymentType

    /**
     * Код карты
     */
    @NotNull
    String code

    /**
     * Имя держателя карты
     */
    String firstName
    /**
     * Фамилия держателя карты
     */
    String lastName
    /**Дата окончания срока действия карты*/
    String expiredDate
    /** Код безопасности (CVC2, CVV2, CID, CSC)*/
    String ccvCode

    public ProcessPaymentByProcessorCommand(String transactionId) {
        this.transactionId = transactionId
    }
}
