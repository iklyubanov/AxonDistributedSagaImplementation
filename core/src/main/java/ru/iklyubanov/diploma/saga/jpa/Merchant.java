package ru.iklyubanov.diploma.saga.jpa;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by ivan on 11/24/2015.
 */
@Entity
@DiscriminatorValue("LEGAL")//юр. лицо
public class Merchant extends Client {

    @NotNull
    @Basic
    private String fullName;

    @NotNull
    @Basic
    private String type;

    @NotNull
    @Basic
    private String area;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
