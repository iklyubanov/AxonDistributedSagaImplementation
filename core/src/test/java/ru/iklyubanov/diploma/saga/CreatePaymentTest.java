package ru.iklyubanov.diploma.saga;

import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import ru.iklyubanov.diploma.saga.axon.PaymentAggregate;
import ru.iklyubanov.diploma.saga.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.axon.event.CreatePaymentEvent;
import ru.iklyubanov.diploma.saga.jpa.util.PaymentType;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class CreatePaymentTest {
    private org.axonframework.test.FixtureConfiguration<PaymentAggregate> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(PaymentAggregate.class);
    }

    @Test
    public void testCreatePaymentAggregate() throws Exception {
        fixture.given().when(new CreatePaymentCommand("123", "1", "777", PaymentType.CONSUMER_PAYMENT))
                .expectEvents(new CreatePaymentEvent("123", "1", "777", PaymentType.CONSUMER_PAYMENT, 2));
    }
}
