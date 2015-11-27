package ru.iklyubanov.diploma.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import ru.iklyubanov.diploma.saga.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.jpa.util.PaymentType;

import java.util.UUID;

/**
 * Created by kliubanov on 23.11.2015.
 */
@SpringBootApplication
@PropertySource("classpath:configuration.properties")
@ImportResource("classpath:META-INF/spring/application-context.xml")
public class CoreConfiguration {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CoreConfiguration.class, args);
        CommandGateway commandGateway = applicationContext.getBean(CommandGateway.class);
        final String paymentId = UUID.randomUUID().toString();
        final String clientId = UUID.randomUUID().toString();
        final String bankId = UUID.randomUUID().toString();
        commandGateway.send(new CreatePaymentCommand(paymentId, clientId, bankId, PaymentType.CONSUMER_PAYMENT));
    }
}
