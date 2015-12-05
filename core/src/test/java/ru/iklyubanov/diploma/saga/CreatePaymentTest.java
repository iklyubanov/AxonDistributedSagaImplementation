package ru.iklyubanov.diploma.saga;

import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import ru.iklyubanov.diploma.saga.axon.PaymentAggregate;
import ru.iklyubanov.diploma.saga.axon.command.CreatePaymentCommand;
import ru.iklyubanov.diploma.saga.axon.command.PaymentCommandHandler;
import ru.iklyubanov.diploma.saga.axon.event.CreatePaymentEvent;
import ru.iklyubanov.diploma.saga.axon.util.TransactionId;
import ru.iklyubanov.diploma.saga.spring.util.PaymentType;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class CreatePaymentTest {
    private org.axonframework.test.FixtureConfiguration<PaymentAggregate> fixture;

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
        createPaymentCommand.setCcvCode("151");
        createPaymentCommand.setCode("4004 5525 8452 5577");
        createPaymentCommand.setCurrencyType("RUB");
        createPaymentCommand.setExpiredDate("10/16");
        createPaymentCommand.setFirstName("IGOR");
        /*createPaymentCommand.setLastName("TALKOV");
        createPaymentCommand.setMerchant("ОАО IRONMAN");*/
        /*createPaymentCommand.setMerchantBankAccount("1551 1515 7715 6771");
        createPaymentCommand.setMerchantBankBIK("040173604");
        createPaymentCommand.setMerchantINN("1354163463463413");*/
        CreatePaymentEvent createPaymentEvent = new CreatePaymentEvent.Builder(transactionId)
                .addAmount(createPaymentCommand.getAmount())
                .addCcvCode(createPaymentCommand.getCcvCode())
                .addCode(createPaymentCommand.getCode())
                .addCurrencyType(createPaymentCommand.getCurrencyType())
                .addExpiredDate(createPaymentCommand.getExpiredDate())
                .addFirstName(createPaymentCommand.getFirstName())
                .build();
        fixture.given().when(createPaymentCommand)
                .expectEvents(createPaymentEvent);
    }
}
