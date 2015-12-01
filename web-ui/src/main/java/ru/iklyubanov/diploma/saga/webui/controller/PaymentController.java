package ru.iklyubanov.diploma.saga.webui.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.iklyubanov.diploma.saga.spring.Payment;
import ru.iklyubanov.diploma.saga.spring.service.PaymentService;
import ru.iklyubanov.diploma.saga.webui.util.Message;

import java.util.Locale;

/**
 * Created by kliubanov on 30.11.2015.
 */
@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final org.slf4j.Logger logger =
            LoggerFactory.getLogger(PaymentController.class);

    private PaymentService paymentService;
    private MessageSource messageSource;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Загружает последние 20 платежей
     * */
    @RequestMapping(method = RequestMethod.GET)
    public String showLastPayments(Model model) {
        logger.info("Подготавливаем последние 20 платежей");
        Page<Payment> lastPayments =  paymentService.findLastPayments();
        model.addAttribute("last_payments", lastPayments);
        logger.info("No. of payments: " + lastPayments.getTotalElements()) ;
        return "payment/last";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String prepareNewPayment(Model model) {
        logger.info("Подготавливаем новый платеж");
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "/new";
    }

    @RequestMapping(value = "/new", params = "form", method = RequestMethod.POST)
    public String saveNewPayment(Payment payment, BindingResult bindingResult, Model model, Locale locale) {
        logger.info("Сохраняем платеж");
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",
                    messageSource.getMessage("contact_save_fail",
                            new Object[]{}, locale)));
            model.addAttribute("contact", payment);
            return "/new";
        }
        return "/new";
    }

}
