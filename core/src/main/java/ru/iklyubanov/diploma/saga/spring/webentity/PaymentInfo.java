package ru.iklyubanov.diploma.saga.spring.webentity;

import ru.iklyubanov.diploma.saga.spring.util.CardType;
import ru.iklyubanov.diploma.saga.spring.util.MonetaryValue;
import ru.iklyubanov.diploma.saga.spring.util.ParentEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by kliubanov on 01.12.2015.
 */
@Entity
@Table(name = "PAYMENT_INFO")
public class PaymentInfo extends ParentEntity{

    /**Тип карты*/
    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    /**К оплате*/
    @Embedded
    private MonetaryValue paymentAmount;

    /**Имя держателя карты*/
    @NotNull
    @Column(name = "f_name")
    private String firstName;

    /**Фамилия держателя карты*/
    @NotNull
    @Column(name = "l_name")
    private String lastName;

    /**Номер карты*/
    @Column(length = 36)
    private String code;

    /**Дата оканчания срока действия карты*/
    @Column(name = "EXP_DATE")
    private java.sql.Date expiredDate;

    /*MasterCard —  код проверки карты (card validation code — CVC2);
Visa — проверка подлинности карты (card verification value CVV2);
Discover —  номер идентификации карты (card identification number CID);
American Express  —  CID, или уникальный номер карты (unique card code) = 4;
Debit Card — CSC, или код безопасности карты (card security code).*/
    /**Код безопасности (MasterCard - CVC2, Visa - CVV2, Discover - CID,
     * American Express - CID или уникальный номер карты, Debit Card - CSC или код безопасности карты)*/
    @Column(name = "CVV_CODE", length = 4)
    private String cvvCode;

    /*Платежный адрес/Billing Address – вписываете тот адрес, который вы использовали при получении карты
    в банке (обычно регистрации или проживания).
    Адрес требуется вводить в транслитерации на английском языке, т.е. русские слова английскими буквами.
    Например, ul. Lenina, dom 367, kv. 12. Иногда, при платеже требуют исключительно цифровые значения
    адреса, в данном случае указываем 367-12.*/
    /**ZIP код (код биллингового адреса кредитной карты)*/
    @Column(name = "ZIP", length = 10)
    private String zipCode;
}
