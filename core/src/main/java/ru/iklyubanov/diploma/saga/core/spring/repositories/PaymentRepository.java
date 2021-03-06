package ru.iklyubanov.diploma.saga.core.spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.iklyubanov.diploma.saga.core.spring.entity.Payment;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kliubanov on 30.11.2015.
 */
@Repository
@Transactional
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {
}
