package ru.iklyubanov.diploma.saga.remote.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.iklyubanov.diploma.saga.core.spring.Bank
import ru.iklyubanov.diploma.saga.core.spring.BankCard

/**
 * Created by ivan on 12/6/2015.
 */
interface BankCardRepository extends CrudRepository<BankCard, Long> {

    @Query("select distinct bc from BankCard bc where bc.bank = :bank and bc.code = :code")
    BankCard findBankCard(@Param("bank") Bank bank, @Param("code") String code)

    @Query("select distinct bc from BankCard bc where bc.bank = :bank and bc.account = :account")
    BankCard findBankCardByAccount(@Param("bank") Bank bank, @Param("account") String account)
}
