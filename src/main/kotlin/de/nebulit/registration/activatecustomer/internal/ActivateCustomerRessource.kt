package de.nebulit.registration.activatecustomer.internal

import de.nebulit.registration.domain.commands.activatecustomer.ActivateCustomerCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ActivateCustomerPayload(var customerId: String)

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611715118
*/
@RestController
class ActivateCustomerRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/activatecustomer")
  fun processDebugCommand(@RequestParam customerId: String): CompletableFuture<Any> {
    return commandGateway.send(ActivateCustomerCommand(customerId))
  }

  @CrossOrigin
  @PostMapping("/activatecustomer/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: ActivateCustomerPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(ActivateCustomerCommand(customerId = payload.customerId))
  }
}
