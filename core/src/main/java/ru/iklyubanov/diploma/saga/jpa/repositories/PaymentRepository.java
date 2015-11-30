package ru.iklyubanov.diploma.saga.jpa.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.iklyubanov.diploma.saga.jpa.Payment;

/**
 * Created by kliubanov on 30.11.2015.
 */
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {
}
