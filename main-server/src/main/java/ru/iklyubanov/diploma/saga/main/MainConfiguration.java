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
@EnableTransactionManagement
@SpringBootApplication
@PropertySource("classpath:configuration.properties")
@ImportResource("classpath:META-INF/spring/application-context.xml")
public class MainConfiguration {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(MainConfiguration.class, args);
        CommandGateway commandGateway = applicationContext.getBean(CommandGateway.class);

        //===============================================
    }

    /*todo классы из теста Distributed CommandBus
    * deprecated*/
    /*public static Integer determineNumberOfCommandLoops() {
        Integer numberOfLoops = readNumberFromCommandlinePlusDefault("Please enter the number of times to send commands", 1);
        System.out.println(String.format("Sending %d times the commands to the cluster.", numberOfLoops));
        return numberOfLoops;
    }

    public static Integer determineLoadFactor() {
        Integer loadFactor = readNumberFromCommandlinePlusDefault("Please enter the load factor to join with", 100);
        System.out.println(String.format("Using a load factor %d when connecting to the cluster.", loadFactor));
        return loadFactor;
    }

    public static Integer readNumberFromCommandlinePlusDefault(String message, int defaultValue) {
        Scanner scanner = new Scanner(System.in);
        Integer numberOfLoops = null;
        while (numberOfLoops == null) {
            System.out.println(String.format(message + ": [%d]", defaultValue));
            String loadFactorString = scanner.nextLine();
            if (loadFactorString.isEmpty()) {
                numberOfLoops = defaultValue;
                break;
            }
            try {
                numberOfLoops = Integer.parseInt(loadFactorString);
            } catch (NumberFormatException e) {
                System.out.println("This is not a number.");
            }
        }
        return numberOfLoops;
    }*/
}
