package ru.iklyubanov.diploma.saga.remote.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.iklyubanov.diploma.saga.core.spring.Bank
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor


/**
 * Created by ivan on 12/6/2015.
 */
interface IssuingBankRepository extends CrudRepository<Bank, Long> {

    @Query("select distinct b from Bank b where b.bik = :bik")
    Bank findBankByBik(@Param("bik") String bik)
}
