package de.nebulit.registration.customerstoactivate.internal

import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModel
import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646643
*/
@RestController
class CustomerstoactivateRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/customerstoactivate")
  fun findReadModel(): CompletableFuture<CustomersToActivateReadModel> {
    return queryGateway.query(
        CustomersToActivateReadModelQuery(), CustomersToActivateReadModel::class.java)
  }
}
