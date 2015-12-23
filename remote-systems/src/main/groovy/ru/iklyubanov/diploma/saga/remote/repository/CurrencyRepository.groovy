package ru.iklyubanov.diploma.saga.remote.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.iklyubanov.diploma.saga.core.spring.entity.Currency

/**
 * Created by ivan on 12/13/2015.
 */
interface CurrencyRepository extends CrudRepository<Currency, Long> {
    @Query("select distinct c from Currency c where c.isoName = :type or c.title = :type")
    Currency findCurrency(@Param("type") String type);
}
