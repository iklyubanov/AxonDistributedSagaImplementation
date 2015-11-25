package ru.iklyubanov.diploma.saga.jpa;

import ru.iklyubanov.diploma.saga.jpa.util.ParentEntity;

import javax.persistence.*;

/**
 * Created by ivan on 11/23/2015.
 */
@Entity
@Table(name = "PAYMENT_SYS_TYPES")
public class PaymentSystemType extends ParentEntity {

    @Basic
    private String type;

    @ManyToMany(mappedBy="projects")
    private Bank bank;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
