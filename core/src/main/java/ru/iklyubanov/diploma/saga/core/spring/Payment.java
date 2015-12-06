package ru.iklyubanov.diploma.saga.core.spring;

import ru.iklyubanov.diploma.saga.core.spring.util.MonetaryValue;
import ru.iklyubanov.diploma.saga.core.spring.util.ParentEntity;
import ru.iklyubanov.diploma.saga.core.spring.util.PaymentState;
import ru.iklyubanov.diploma.saga.core.spring.util.PaymentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by kliubanov on 24.11.2015.
 */
@Entity
@Table(name = "PAYMENTS")
public class Payment extends ParentEntity {

    @Column(name = "REG_DATE")
    private java.sql.Date date;

    @ManyToOne(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="B_CARD_ID")
    private BankCard bankCard;

    @ManyToOne(fetch= FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="CLIENT_ID")
    private Client client;

    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="PAY_PROC_ID")
    private PaymentProcessor paymentProcessor;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="MERCHANT_ID")
    private Merchant merchant;

    /**К оплате*/
    @Embedded
    private MonetaryValue paymentAmount;

    @Basic
    private String info;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public MonetaryValue getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(MonetaryValue paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}