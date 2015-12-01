package ru.iklyubanov.diploma.saga.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iklyubanov.diploma.saga.spring.Payment;
import ru.iklyubanov.diploma.saga.spring.repositories.PaymentRepository;
import ru.iklyubanov.diploma.saga.spring.webentity.PaymentInfo;

/**
 * Created by kliubanov on 01.12.2015.
 */
@Repository
@Transactional
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Payment> findLastPayments() {
        Page<Payment> lastPayments =  paymentRepository.findAll(new PageRequest(1, 20));
        return lastPayments;
    }

    @Override
    public Payment createNewPayment(PaymentInfo paymentInfo) {
        Payment payment = new Payment();
        //todo здесь наверно нужно вызвать сагу и вы выполнять процесс создание в ней
        return paymentRepository.save(payment);
    }
}
