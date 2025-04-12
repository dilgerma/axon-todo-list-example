package de.nebulit.registration.readmodel.internal

import de.nebulit.registration.events.CustomerRegisteredEvent
import de.nebulit.registration.readmodel.CustomersReadModelEntity
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface CustomersReadModelRepository : JpaRepository<CustomersReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646459
*/
@Component
class CustomersReadModelProjector(var repository: CustomersReadModelRepository) {

  @EventHandler
  fun on(event: CustomerRegisteredEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.customerId).orElse(CustomersReadModelEntity())
    entity
        .apply {
          customerId = event.customerId
          name = event.name
          email = event.email
        }
        .also { this.repository.save(it) }
  }
}
