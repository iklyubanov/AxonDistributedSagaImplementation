package ru.iklyubanov.diploma.saga.remote.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.iklyubanov.diploma.saga.core.spring.PaymentProcessor


/**
 * Created by ivan on 12/6/2015.
 */
interface PaymentProcessorRepository extends CrudRepository<PaymentProcessor, Long> {

    @Query("select distinct pp from PaymentProcessor pp where pp.maxTransactionsCount > pp.currentTransactionsCount")
    PaymentProcessor findFreeProcessor();
}
