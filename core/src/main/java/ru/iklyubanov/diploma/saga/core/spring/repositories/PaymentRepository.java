package ru.iklyubanov.diploma.saga.core.spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.iklyubanov.diploma.saga.core.spring.entity.Payment;

/**
 * Created by kliubanov on 30.11.2015.
 */
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {

}
