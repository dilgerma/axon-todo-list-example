package de.nebulit.registration.readmodel.internal

import de.nebulit.registration.readmodel.CustomersReadModel
import de.nebulit.registration.readmodel.CustomersReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646459
*/
@RestController
class ReadmodelRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/readmodel/{id}")
  fun findReadModel(@PathVariable("id") customerId: String): CompletableFuture<CustomersReadModel> {
    return queryGateway.query(CustomersReadModelQuery(customerId), CustomersReadModel::class.java)
  }
}
