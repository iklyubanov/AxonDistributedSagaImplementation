package ru.iklyubanov.diploma.saga.jpa;

import ru.iklyubanov.diploma.saga.jpa.util.ParentEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Created by ivan on 11/24/2015.
 */
public class Bank extends ParentEntity {

    /*БИК*/
    @NotNull
    @Basic
    private String bik;

    @NotNull
    @Basic
    private String name;

    @Column(name = "full_name")
    private String fullName;

    @Basic
    private String address;

    @Basic
    private String fax;

    @Basic
    private String email;

    @Basic
    private String site;

    @Column(name = "swift_code", length = 16)
    private String swiftCode;

    /*Расчетный счет*/
    @NotNull
    @Column(name = "settlement_account", length = 21)
    private String settlementAccount;

    /*КПП*/
    @NotNull
    @Basic
    private String kpp;

    /*ИНН*/
    @NotNull
    @Basic
    private String inn;

    /*Корреспондентский счет */
    @NotNull
    @Column(name = "correspondent_account", length = 21)
    private String correspondentAccount;

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getSettlementAccount() {
        return settlementAccount;
    }

    public void setSettlementAccount(String settlementAccount) {
        this.settlementAccount = settlementAccount;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }
}
