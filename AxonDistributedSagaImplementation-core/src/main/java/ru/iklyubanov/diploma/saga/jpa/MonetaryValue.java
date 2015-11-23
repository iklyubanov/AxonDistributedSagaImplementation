package ru.iklyubanov.diploma.saga.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by kliubanov on 23.11.2015.
 */
@Embeddable
public class MonetaryValue implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    //
    public static final int MAX_CURRENCY_LEN = 5;

    @Column(name="currency_type", length=MAX_CURRENCY_LEN)
    private String currencyType;
    @Column(name="amount", precision=19, scale=2)
    private BigDecimal amount;

    public MonetaryValue() {
    }

    public MonetaryValue(String currencyType, BigDecimal amount) {
        this.currencyType = currencyType;
        this.amount = amount;
    }

    public MonetaryValue(String currencyType, String amount) {
        this.currencyType = currencyType;
        this.amount = new BigDecimal(amount);
    }

    /**
     * @return the currency Type
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * @param currencyType the currency to set
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
