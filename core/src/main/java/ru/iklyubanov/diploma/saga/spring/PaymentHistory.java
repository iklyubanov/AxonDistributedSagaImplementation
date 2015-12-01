package ru.iklyubanov.diploma.saga.spring;

import ru.iklyubanov.diploma.saga.spring.util.ParentEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by kliubanov on 24.11.2015.
 */
@Entity
public class PaymentHistory extends ParentEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private Client client;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="PAYMENT_ID")
    private Payment payment;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
