package de.nebulit.registration.registercustomer.internal

import de.nebulit.registration.domain.commands.registercustomer.RegisterCustomerCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class RegisterCustomerPayload(var customerId: String, var name: String, var email: String)

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646358
*/
@RestController
class RegisterCustomerRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/registercustomer")
  fun processDebugCommand(
      @RequestParam customerId: String,
      @RequestParam name: String,
      @RequestParam email: String
  ): CompletableFuture<Any> {
    return commandGateway.send(RegisterCustomerCommand(customerId, name, email))
  }

  @CrossOrigin
  @PostMapping("/registercustomer/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: RegisterCustomerPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        RegisterCustomerCommand(
            customerId = payload.customerId, name = payload.name, email = payload.email))
  }
}
