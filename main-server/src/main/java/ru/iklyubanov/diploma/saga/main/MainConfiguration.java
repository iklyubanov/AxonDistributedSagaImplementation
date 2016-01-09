package ru.iklyubanov.diploma.saga.main;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by kliubanov on 23.11.2015.
 */
/*@EnableTransactionManagement
@SpringBootApplication
@PropertySource("classpath:configuration.properties")
@ImportResource("classpath:META-INF/spring/application-context.xml")*/
@Deprecated
public class MainConfiguration {

    /*public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(MainConfiguration.class, args);
        //CommandGateway commandGateway = applicationContext.getBean(CommandGateway.class);

        //===============================================
    }*/


}
