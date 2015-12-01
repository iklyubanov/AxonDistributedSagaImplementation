package ru.iklyubanov.diploma.saga.spring.service;

import org.springframework.data.domain.Page;
import ru.iklyubanov.diploma.saga.spring.Payment;

/**
 * Created by kliubanov on 01.12.2015.
 */
public interface PaymentService {

    Page<Payment> findLastPayments();

    Payment createNewPayment(Payment payment);
}
