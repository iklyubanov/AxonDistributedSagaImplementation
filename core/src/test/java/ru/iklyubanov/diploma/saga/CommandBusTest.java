package ru.iklyubanov.diploma.saga;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.domain.EventContainer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.junit.Test;

import java.io.File;

/**
 * Created by ivan on 12/3/2015.
 */
public class CommandBusTest {

    @Test
    public void test() {
        // We use the simple Command Bus.
        // There are different implementation available. For example axon provides a distributed command bus that can be used to distribute commands over multiple nodes
//        CommandBus commandBus = new SimpleCommandBus();
//
//        // The friendlier API to send commands with
//        CommandGateway commandGateway = new DefaultCommandGateway(commandBus);
//
//        // You may skip this as it may not pertain to your question but since we are using event sourcing,
//        // we need a place to store the events. we'll store Events on the FileSystem, in the "events" folder
//        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("./events")));
//
//        // a Simple Event Bus will do
//        EventBus eventBus = new SimpleEventBus();
//
//        // You may skip this as it may not pertain to your question but since event sourcing is
//        // used in this example we need to configure the repository: an event sourcing repository.
//        EventSourcingRepository<MyAggregate> repository = new EventSourcingRepository<MyAggregate>(MyAggregate.class,
//                eventStore);
//
//        // Sets the event bus to which newly stored events should be published
//        repository.setEventBus(eventBus);
//
//        // Tells Axon that MyAggregate can handle commands
//        AggregateAnnotationCommandHandler.subscribe(MyAggregate.class, repository, commandBus);
//
//        // This is the part you need. With this We register an event listener to be able to handle
//        // events published on to an event bus. In this case EventContainer.
//        AnnotationEventListenerAdapter.subscribe(new EventContainer(), eventBus);
//
//        // and let's send some Commands on the CommandBus.
//        commandGateway.send(id, circle);
    }
}
