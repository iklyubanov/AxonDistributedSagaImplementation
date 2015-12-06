package ru.iklyubanov.diploma.saga.remote

import org.axonframework.commandhandling.annotation.CommandHandler
import org.springframework.stereotype.Component

/**
 * Created by ivan on 12/6/2015.
 */
@Component
class RemoteCommandHandler {

    @CommandHandler
    public void handle(ProcessPay) {

    }

}
