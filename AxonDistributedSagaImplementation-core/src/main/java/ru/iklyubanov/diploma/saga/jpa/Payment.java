package ru.iklyubanov.diploma.saga.jpa;

import ru.iklyubanov.diploma.saga.jpa.util.ParentEntity;
import ru.iklyubanov.diploma.saga.jpa.util.PaymentState;
import ru.iklyubanov.diploma.saga.jpa.util.PaymentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by kliubanov on 24.11.2015.
 */
@Entity
@Table(name = "PAYMENTS")
public class Payment extends ParentEntity {

    @Column(name = "DATE")
    private java.sql.Date date;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="B_CARD_ID")
    private BankCard bankCard;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAY_PROC_ID")
    private Bank bank;
}
