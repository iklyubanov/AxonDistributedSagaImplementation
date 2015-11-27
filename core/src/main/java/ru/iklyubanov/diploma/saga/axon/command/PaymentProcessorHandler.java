package ru.iklyubanov.diploma.saga.axon.command;

import org.axonframework.commandhandling.annotation.CommandHandler;

/**
 * Step 2: YapStone to Payment Processor

 YapStone sends the transaction details to our payment processor using a dedicated link that’s monitored 24 x 7 x 365
 to ensure that processing is not interrupted.

 Step 3: Payment Processor to Card Networks to Card Issuing Bank

 Payment details are validated by the payment processor by sending them through the credit or debit card networks
 (Visa, MasterCard, American Express, Discover), which forward them to the card-issuing bank to be authorized.

 * Created by kliubanov on 27.11.2015.
 */
public class PaymentProcessorHandler {

    /*@Autowired
    private Repository<SeatsAvailability> seatsAvailabilityRepository;

    @CommandHandler
    public void handle(MakeSeatsReservation command) {
        SeatsAvailability seatsAvailability = seatsAvailabilityRepository.load(command.getConferenceId());
        seatsAvailability.makeSeatsReservation(command.getSeats(), command.getOrderId());
    }*/

    @CommandHandler
    public void handle(ProcessPaymentByProcessorCommand command) {
        /*SeatsAvailability seatsAvailability = seatsAvailabilityRepository.load(command.getConferenceId());
        seatsAvailability.makeSeatsReservation(command.getSeats(), command.getOrderId());*/
    }
}
