package ru.iklyubanov.diploma.saga.main;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.jgroups.stack.GossipRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.net.BindException;
import java.util.Scanner;

/**
 * Created by kliubanov on 23.11.2015.
 */
@SpringBootApplication
@PropertySource("classpath:META-INF/spring/configuration.properties")
@ImportResource("classpath:META-INF/spring/main-application-context.xml")
public class MainConfiguration {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(MainConfiguration.class, args);
        CommandGateway commandGateway = applicationContext.getBean(CommandGateway.class);


        //TODO Запуск Distributed CommandBus With Spring
        //==============================================
        // Load the Load factor from the command line or use default 100
        Integer loadFactor = determineLoadFactor();

        System.setProperty("loadFactor", loadFactor.toString());

        // Start the GossipRouter if it is not already running
        GossipRouter gossipRouter = new GossipRouter("127.0.0.1", 7800);//it should be in config tcp_gossip
        try {
            gossipRouter.start();
        } catch (BindException e) {
            System.out.println("Gossip router is already started in another JVM instance.");
        }

        // Load the spring beans from the xml configuration file.
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("distributed-config.xml");

        // Obtain the gateway from the context to send commands.
        //CommandGateway commandGateway = applicationContext.getBean("commandGateway", CommandGateway.class);

        // Load the amount of times to send the commands from the command line or use default 1
        Integer numberOfCommandLoops = determineNumberOfCommandLoops();

        /*for (int i = 0; i < numberOfCommandLoops; i++) {
            //CommandGenerator.sendCommands(commandGateway);
        }*/
        //===============================================
    }

    /*todo классы из теста Distributed CommandBus*/
    public static Integer determineNumberOfCommandLoops() {
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
    }
}
