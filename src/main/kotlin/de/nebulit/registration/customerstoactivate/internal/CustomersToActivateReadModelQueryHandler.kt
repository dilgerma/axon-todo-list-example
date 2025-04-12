package de.nebulit.registration.customerstoactivate.internal

import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModel
import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646643
*/
@Component
class CustomersToActivateReadModelQueryHandler(
    private val repository: CustomersToActivateReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: CustomersToActivateReadModelQuery): CustomersToActivateReadModel? {
    return CustomersToActivateReadModel(repository.findAll())
  }
}
