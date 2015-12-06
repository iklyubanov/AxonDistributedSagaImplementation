package ru.iklyubanov.diploma.saga.core.spring;

import ru.iklyubanov.diploma.saga.core.spring.util.ParentEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ivan on 11/24/2015.
 */
@Entity
@Table(name="PAY_PROCESSORS")
public class PaymentProcessor extends ParentEntity {

    @Basic
    private String name;

    @OneToMany(mappedBy="paymentProcessor")
    private List<Payment> payments;

    //TODO may be add loading parameter

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
}
