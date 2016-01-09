package ru.iklyubanov.diploma.saga.webui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ivan on 1/9/2016.
 */
@EnableTransactionManagement
@SpringBootApplication
@PropertySource("classpath:configuration.properties")
@ImportResource("classpath:/META-INF/spring/application-context.xml")
@EnableJpaRepositories("ru.iklyubanov.diploma.saga.core.spring.repositories")
public class App extends SpringBootServletInitializer
{

    public static void main( String[] args )    {
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(appClass);
    }

    private static Class<App> appClass = App.class;
}