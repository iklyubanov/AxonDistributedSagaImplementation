package ru.iklyubanov.diploma.saga.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by kliubanov on 23.11.2015.
 */
@Entity
@Table(name="USERS")
public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Column(length = 36)
    private String identifier;

    @NotNull
    @Column(name = "f_name")
    private String firstName;

    @NotNull
    @Column(name = "l_name")
    private String lastName;

    @OneToMany(mappedBy="user")
    private List<BankCard> bankCards;

    @Column(name = "phone_num", length = 15)
    private String phone;

    @Column(name = "m_phone_num", length = 15)
    private String mobilePhone;

    @Basic
    private String email;

    /*TODO we can encript it in EntityListener if need*/
    @Column(length = 36)
    private String password;

    @Column(name = "bill_adr")
    private String billingAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
