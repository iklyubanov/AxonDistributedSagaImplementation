package ru.iklyubanov.diploma.saga.core.spring.entity;

import ru.iklyubanov.diploma.saga.core.spring.util.ParentEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ivan on 11/23/2015.
 */
@Entity
@Table(name = "PAYMENT_SYS_TYPES")
public class PaymentSystemType extends ParentEntity {

    @Basic
    private String type;

    @ManyToMany(mappedBy="paymentSystemTypes")
    private List<Bank> banks;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
