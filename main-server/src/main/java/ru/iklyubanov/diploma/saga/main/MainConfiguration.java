package ru.iklyubanov.diploma.saga.main;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kliubanov on 23.11.2015.
 */
@SpringBootApplication
@PropertySource("classpath:configuration.properties")
@ImportResource("classpath:META-INF/spring/main-application-context.xml")
public class MainConfiguration {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MainConfiguration.class, args);
        CommandGateway commandGateway = applicationContext.getBean(CommandGateway.class);
        /*final String paymentId = UUID.randomUUID().toString();
        final String clientId = UUID.randomUUID().toString();
        final String bankId = UUID.randomUUID().toString();*/

        //commandGateway.send(new CreatePaymentCommand());
    }
}
