package de.nebulit.registration.activatecustomer.internal

import de.nebulit.registration.activatecustomer.CustomerActivationTriggered
import de.nebulit.registration.common.Processor
import de.nebulit.registration.domain.commands.activatecustomer.ActivateCustomerCommand
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611715115
*/
@Component
class ActivateCustomerProcessor : Processor {
  var logger = KotlinLogging.logger {}

  @Autowired lateinit var commandGateway: CommandGateway
  @Autowired lateinit var queryGateway: QueryGateway

  @EventHandler
  fun on(event: CustomerActivationTriggered) {
    commandGateway.sendAndWait<Any>(ActivateCustomerCommand(event.customerId))
  }
}
