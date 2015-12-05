package ru.iklyubanov.diploma.saga.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import ru.iklyubanov.diploma.saga.axon.util.TransactionId;

/**
 * Step 2: YapStone to Payment Processor

 YapStone sends the transaction details to our payment processor using a dedicated link that’s monitored 24 x 7 x 365
 to ensure that processing is not interrupted.

 Step 3: Payment Processor to Card Networks to Card Issuing Bank

 Payment details are validated by the payment processor by sending them through the credit or debit card networks
 (Visa, MasterCard, American Express, Discover), which forward them to the card-issuing bank to be authorized.

 * Created by kliubanov on 27.11.2015.
 */
public class ProcessPaymentByProcessorCommand {

    @TargetAggregateIdentifier
    private final String processorId;
    private final TransactionId transactionId;
    //todo change to real transaction details
    private final String transactionDetails;

    public ProcessPaymentByProcessorCommand(String processorId, TransactionId transactionId, String transactionDetails) {
        this.processorId = processorId;
        this.transactionId = transactionId;
        this.transactionDetails = transactionDetails;
    }

    public String getProcessorId() {
        return processorId;
    }

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }
}
