package ru.iklyubanov.diploma.saga.main.service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iklyubanov.diploma.saga.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.spring.Payment;
import ru.iklyubanov.diploma.saga.spring.repositories.PaymentRepository;

/**
 * Created by kliubanov on 01.12.2015.
 */
@Repository
@Transactional
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    @Autowired
    private CommandGateway commandGateway;

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

    @Override //todo нужно убрать возвращаемый тип или передавать сообщение получаемое из колбэка
    public void createNewPayment(CreatePaymentCommand createPaymentCommand) {
        //todo здесь наверно нужно вызвать сагу и выполнять процесс создание в ней
        commandGateway.send(createPaymentCommand);
        //commandGateway.send(new ModifyTaskTitleCommand(identifier, request.getTitle()));
        //return paymentRepository.save(payment);
    }
}
