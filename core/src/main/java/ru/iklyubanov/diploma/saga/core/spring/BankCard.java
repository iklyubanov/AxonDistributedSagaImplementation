package ru.iklyubanov.diploma.saga.core.spring;

import ru.iklyubanov.diploma.saga.core.spring.util.CardStatus;
import ru.iklyubanov.diploma.saga.core.spring.util.CardType;
import ru.iklyubanov.diploma.saga.core.spring.util.ParentEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by kliubanov on 23.11.2015.
 */
@Entity
@Table(name = "BCARDS")
public class BankCard extends ParentEntity {

    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BANK_ID")
    private Bank bank;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    private Client client;

    /*@Column(precision=19, scale=2)
    private BigDecimal balance;*/
    @OneToOne
    @JoinColumn(name="WALLET_ID")
    private Wallet wallet;

    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @Column(name = "EXP_DATE")
    private String expiredDate;

    @Column(length = 36, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SYS_TYPE_ID")
    private PaymentSystemType paymentSystemType;

    @Column(name = "CCV_CODE", length = 3)
    private String ccvCode;

    @Column(name = "ZIP", length = 10)
    private String zipCode;

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PaymentSystemType getPaymentSystemType() {
        return paymentSystemType;
    }

    public void setPaymentSystemType(PaymentSystemType paymentSystemType) {
        this.paymentSystemType = paymentSystemType;
    }

    public String getCcvCode() {
        return ccvCode;
    }

    public void setCcvCode(String ccvCode) {
        this.ccvCode = ccvCode;
    }

    public String getHidenCode() {
        if(code == null) {
            return "";
        }
        return "*" + code.substring(code.length() - 3);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
