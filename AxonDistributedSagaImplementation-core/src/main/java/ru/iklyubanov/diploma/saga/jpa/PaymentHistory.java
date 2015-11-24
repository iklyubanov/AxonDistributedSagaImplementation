package ru.iklyubanov.diploma.saga.jpa;

import ru.iklyubanov.diploma.saga.jpa.util.ParentEntity;

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
    private User user;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="PAYMENT_ID")
    private Payment payment;
}
