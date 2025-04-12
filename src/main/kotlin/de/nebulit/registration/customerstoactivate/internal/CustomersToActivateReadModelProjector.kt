package de.nebulit.registration.customerstoactivate.internal

import de.nebulit.registration.activatecustomer.CustomerActivationTriggered
import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModelEntity
import de.nebulit.registration.events.CustomerActivatedEvent
import de.nebulit.registration.events.CustomerRegisteredEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.gateway.EventGateway
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface CustomersToActivateReadModelRepository :
    JpaRepository<CustomersToActivateReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646643
*/
@Component
class CustomersToActivateReadModelProjector(
    var repository: CustomersToActivateReadModelRepository,
    val eventGateway: EventGateway
) {

  @EventHandler
  fun on(event: CustomerRegisteredEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.customerId).orElse(CustomersToActivateReadModelEntity())
    entity
        .apply {
          customerId = event.customerId
          name = event.name
          email = event.email
        }
        .also { this.repository.save(it) }

      eventGateway.publish(CustomerActivationTriggered(event.customerId))
  }

  @EventHandler
  fun on(event: CustomerActivatedEvent) {
    // throws exception if not available (adjust logic)
    repository.findById(event.customerId).ifPresent {
      it.activated = true
      repository.save(it)
    }
  }
}
