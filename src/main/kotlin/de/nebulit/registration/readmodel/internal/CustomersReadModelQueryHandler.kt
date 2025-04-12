package de.nebulit.registration.readmodel.internal

import de.nebulit.registration.readmodel.CustomersReadModel
import de.nebulit.registration.readmodel.CustomersReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646459
*/
@Component
class CustomersReadModelQueryHandler(private val repository: CustomersReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: CustomersReadModelQuery): CustomersReadModel? {

    if (!repository.existsById(query.customerId)) {
      return null
    }
    return CustomersReadModel(repository.findById(query.customerId).get())
  }
}
