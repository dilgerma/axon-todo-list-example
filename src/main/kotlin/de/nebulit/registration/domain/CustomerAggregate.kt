package de.nebulit.registration.domain

import de.nebulit.registration.domain.commands.activatecustomer.ActivateCustomerCommand
import de.nebulit.registration.domain.commands.registercustomer.RegisterCustomerCommand
import de.nebulit.registration.events.CustomerActivatedEvent
import de.nebulit.registration.events.CustomerRegisteredEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class CustomerAggregate {

  @AggregateIdentifier var customerId: String? = null

  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: RegisterCustomerCommand) {

    AggregateLifecycle.apply(
        CustomerRegisteredEvent(
            customerId = command.customerId, name = command.name, email = command.email))
  }

  @EventSourcingHandler
  fun on(event: CustomerRegisteredEvent) {
    // handle event
    customerId = event.customerId
  }

  @CommandHandler
  fun handle(command: ActivateCustomerCommand) {

    AggregateLifecycle.apply(CustomerActivatedEvent(customerId = command.customerId))
  }

  @EventSourcingHandler
  fun on(event: CustomerActivatedEvent) {
    // handle event
    customerId = event.customerId
  }
}
