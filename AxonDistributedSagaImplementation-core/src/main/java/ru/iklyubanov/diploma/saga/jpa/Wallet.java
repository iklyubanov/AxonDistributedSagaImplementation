package ru.iklyubanov.diploma.saga.jpa;

import ru.iklyubanov.diploma.saga.jpa.util.MonetaryValue;
import ru.iklyubanov.diploma.saga.jpa.util.ParentEntity;

import javax.persistence.*;

/**
 * Created by kliubanov on 23.11.2015.
 */
public class Wallet extends ParentEntity {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRENCY_ID")
    private Currency currency;

    @Embedded
    private MonetaryValue monetaryValue;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="wallet")
    private BankCard bankCard;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public MonetaryValue getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(MonetaryValue monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }
}
