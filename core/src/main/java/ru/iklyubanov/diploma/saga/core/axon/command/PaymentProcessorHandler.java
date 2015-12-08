package ru.iklyubanov.diploma.saga.core.axon.command;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.stereotype.Component;

/**
 * Created by kliubanov on 27.11.2015.
 */
@Component
public class PaymentProcessorHandler {

    /*@Autowired
    private Repository<SeatsAvailability> seatsAvailabilityRepository;

    @CommandHandler
    public void handle(MakeSeatsReservation command) {
        SeatsAvailability seatsAvailability = seatsAvailabilityRepository.load(command.getConferenceId());
        seatsAvailability.makeSeatsReservation(command.getSeats(), command.getOrderId());
    }*/

    /*Step 2: YapStone to Payment Processor

 YapStone sends the transaction details to our payment processor using a dedicated link that’s monitored 24 x 7 x 365
 to ensure that processing is not interrupted.

 Step 3: Payment Processor to Card Networks to Card Issuing Bank

 Payment details are validated by the payment processor by sending them through the credit or debit card networks
 (Visa, MasterCard, American Express, Discover), which forward them to the card-issuing bank to be authorized.*/
    @CommandHandler
    public void handle(ProcessPaymentByProcessorCommand command) {
        //todo command.getProcessorId() get processor from repository
        //todo check recvisite
    }

    /*Step 4: Card Issuing Bank
The card-issuing bank approves/denies the transaction based on card status and whether the transaction
is within the cardholder’s credit limit or not.

Step 5: Payment Processor to Card Networks to YapStone
If payment gets approved, the card issuer charges the user and forwards the funds to the card networks
less any fees to cover costs such as credit risk and rewards. The card networks then relay the
transaction-approved status back to the payment processor, who notifies YapStone and sends
on the funds less applicable fees.

Step 6: YapStone to User
YapStone’s lets the user know if the transaction is approved or denied through the online payment interface.
If payment is denied, YapStone’s payment gateway notifies the site or app and because the card-issuing bank
did not authorize the transaction, the process comes to a halt.*/

    @CommandHandler
    public void handle(CheckNewPaymentByIssuingBankCommand command) {
        //todo command.getProcessorId() get processor from repository
        //todo check recvisite
    }

    @CommandHandler
    public void handle(SendMoneyByCardNetworkCommand command) {
        //todo command.getProcessorId() get processor from repository
        //todo check recvisite
    }
}
