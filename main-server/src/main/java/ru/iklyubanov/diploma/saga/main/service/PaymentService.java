package ru.iklyubanov.diploma.saga.main.service;

import org.springframework.data.domain.Page;
import ru.iklyubanov.diploma.saga.gcore.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.core.spring.Payment;

/**
 * Created by kliubanov on 01.12.2015.
 */
public interface PaymentService {

    Page<Payment> findLastPayments();

    void createNewPayment(CreatePaymentCommand createPaymentCommand);
}
