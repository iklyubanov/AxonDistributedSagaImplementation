package ru.iklyubanov.diploma.saga.gcore.axon.command

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId
import ru.iklyubanov.diploma.saga.gcore.annotation.Command

import javax.validation.constraints.NotNull

/**
 * Created by kliubanov on 27.11.2015.
 */
@Command
public class CreatePaymentCommand {

    @NotNull
    @TargetAggregateIdentifier
    TransactionId transactionId
    /**
     * Тип карты (PaymentSystemType)
     */
    @NotNull
    String paymentSystemType
    /**
     * К оплате
     */
    @NotNull
    BigDecimal amount
    /**
     * Валюта
     */
    @NotNull
    String currencyType
    /**Код карты*/
    @NotNull
    String code
    /**
     * Имя держателя карты
     */
    @NotNull
    String firstName
    /**
     * Фамилия держателя карты
     */
    @NotNull
    String lastName
    /**Дата окончания срока действия карты*/
    @NotNull
    String expiredDate
    /** Код безопасности (CVC2, CVV2, CID, CSC)*/
    @NotNull
    String ccvCode
    /** Тип платежа*/
    @NotNull
    String paymentType
    /** БИК банка получателя*/
    @NotNull
    String merchantBankBIK
    /** Номер счета в банке-получателе
     * для организации - десятизначный цифровой код
     * для физического лица - двенадцатизначный цифровой код:*/
    @NotNull
    String merchantBankAccount
    /** Получатель (не > 160 симв)*/
    @NotNull
    String merchant
    /** ИНН (не > 160 симв)*/
    @NotNull
    String merchantINN
}
