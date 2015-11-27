package ru.iklyubanov.diploma.saga;

import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.junit.Before;
import ru.iklyubanov.diploma.saga.axon.saga.PaymentProcessingSaga;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class PaymentProcessingSagaTest {

    private AnnotatedSagaTestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AnnotatedSagaTestFixture(PaymentProcessingSaga.class);
    }


}
