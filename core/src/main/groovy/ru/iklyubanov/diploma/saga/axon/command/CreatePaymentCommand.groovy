package ru.iklyubanov.diploma.saga.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.annotation.Command

/**
 * Created by kliubanov on 27.11.2015.
 */
@Command
public class CreatePaymentCommand {

    @TargetAggregateIdentifier
    String paymentId
    /**
     * Тип карты
     */
    String cardType
    /**
     * К оплате
     */
    BigDecimal amount
    /**
     * Валюта
     */
    String currencyType
    /**
     * Имя держателя карты
     */
    String firstName
    /**
     * Фамилия держателя карты
     */
    String lastName
}
