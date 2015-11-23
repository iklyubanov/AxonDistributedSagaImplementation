package ru.iklyubanov.diploma.saga.jpa;

import ru.iklyubanov.diploma.saga.jpa.util.CardStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by kliubanov on 23.11.2015.
 */
@Entity
@Table(name = "BCARDS")
public class BankCard implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Column(length = 36)
    private String identifier;

    //TODO type ManyToOne
    //TODO bank ManyToOne

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    /*@Column(precision=19, scale=2)
    private BigDecimal balance;*/
    @OneToOne
    @JoinColumn(name="WALLET_ID")
    private Wallet wallet;

    @NotNull
    @Basic
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @Column(name = "EXP_DATE")
    private java.sql.Date expiredDate;

    @Basic
    @Column(length = 36)
    private String code;
}
