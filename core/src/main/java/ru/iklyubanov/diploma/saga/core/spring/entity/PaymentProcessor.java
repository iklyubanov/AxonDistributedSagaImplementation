package ru.iklyubanov.diploma.saga.core.spring.entity;

import ru.iklyubanov.diploma.saga.core.spring.util.ParentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 11/24/2015.
 */
@Entity
@Table(name = "PAY_PROCESSORS")
@NamedQuery(name = "PaymentProcessor.findFreeProcessor", query =
        "select distinct pp from PaymentProcessor pp where pp.maxTransactionsCount > pp.currentTransactionsCount")
public class PaymentProcessor extends ParentEntity {

    @Basic
    private String name;

    @OneToMany(mappedBy = "paymentProcessor",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Payment> payments;

    @Basic
    @Column(name = "MAX_TRANSACT_COUNT")
    private int maxTransactionsCount;

    @Basic
    @Column(name = "CUR_TRANSACT_COUNT")
    private int currentTransactionsCount = 0;

    public PaymentProcessor() {
        if (payments == null) {
            payments = new ArrayList<Payment>();
        }
    }

    @PrePersist
    void preInsert() {
        if (maxTransactionsCount == 0) {
            //set default value
            maxTransactionsCount = 100;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public int getMaxTransactionsCount() {
        return maxTransactionsCount;
    }

    public void setMaxTransactionsCount(int maxTransactionsCount) {
        this.maxTransactionsCount = maxTransactionsCount;
    }

    public int getCurrentTransactionsCount() {
        return currentTransactionsCount;
    }

    public void setCurrentTransactionsCount(int currentTransactionsCount) {
        this.currentTransactionsCount = currentTransactionsCount;
    }
}
