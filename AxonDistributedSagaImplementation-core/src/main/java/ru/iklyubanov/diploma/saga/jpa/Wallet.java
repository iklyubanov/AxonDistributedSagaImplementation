package ru.iklyubanov.diploma.saga.jpa;

import javax.persistence.*;

/**
 * Created by kliubanov on 23.11.2015.
 */
public class Wallet implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    private String identifier;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CURRENCY_ID")
    private Currency currency;

    @Embedded
    private MonetaryValue monetaryValue;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="wallet")
    private BankCard bankCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
