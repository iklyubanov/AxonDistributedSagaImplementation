package ru.iklyubanov.diploma.saga.webui.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.iklyubanov.diploma.saga.jpa.Payment;
import ru.iklyubanov.diploma.saga.jpa.repositories.PaymentRepository;

/**
 * Created by kliubanov on 30.11.2015.
 */
@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final org.slf4j.Logger logger =
            LoggerFactory.getLogger(PaymentController.class);

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Загружает последние 20 платежей
     * */
    @RequestMapping(method = RequestMethod.GET)
    public String showLastPayments(Model model) {
        Page<Payment> lastPayments =  paymentRepository.findAll(new PageRequest(1, 20));
        model.addAttribute("last_payments", lastPayments);
        logger.info("No. of payments: " + lastPayments.getTotalElements()) ;
        return "payment/last";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String prepareNewPayment(Model model) {

        return "/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveNewPayment(Model model) {

        return "/new";
    }

}
