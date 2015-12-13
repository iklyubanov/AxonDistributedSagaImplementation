package ru.iklyubanov.diploma.saga.main.service;

import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import ru.iklyubanov.diploma.saga.core.axon.aggregate.PaymentAggregate;
import ru.iklyubanov.diploma.saga.gcore.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.core.axon.command.PaymentCommandHandler;
import ru.iklyubanov.diploma.saga.gcore.axon.event.CreatePaymentEvent;
import ru.iklyubanov.diploma.saga.core.axon.util.TransactionId;

import java.math.BigDecimal;

/**TODO ТЕСТ НЕ РАБОТАЕТ ИЗ-ЗА КРИВОЙ СВЯЗИ ЧЕРЕЗ GRADLE
 * Created by ivan on 12/6/2015.
 */
public class PaymentTest {
    /*private org.axonframework.test.FixtureConfiguration<PaymentAggregate> fixture;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(PaymentAggregate.class);
        PaymentCommandHandler commandHandler = new PaymentCommandHandler();
        commandHandler.setRepository(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    @Test
    public void testCreatePaymentAggregate() throws Exception {
        TransactionId transactionId = new TransactionId();
        CreatePaymentCommand createPaymentCommand = new CreatePaymentCommand();
        createPaymentCommand.setTransactionId(transactionId);
        createPaymentCommand.setAmount(new BigDecimal(100));

        CreatePaymentEvent createPaymentEvent = new CreatePaymentEvent.Builder(transactionId)
                .addAmount(createPaymentCommand.getAmount())
                .build();
        fixture.given().when(createPaymentCommand)
                .expectEvents(createPaymentEvent);
    }*/
}
