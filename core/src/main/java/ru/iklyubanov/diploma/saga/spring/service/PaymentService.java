package ru.iklyubanov.diploma.saga.spring.service;

import org.springframework.data.domain.Page;
import ru.iklyubanov.diploma.saga.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.spring.Payment;
import ru.iklyubanov.diploma.saga.spring.webentity.PaymentInfo;

/**
 * Created by kliubanov on 01.12.2015.
 */
public interface PaymentService {

    Page<Payment> findLastPayments();

    void createNewPayment(CreatePaymentCommand createPaymentCommand);
}
