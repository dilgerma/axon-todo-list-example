package de.nebulit.registration.customerstoactivate

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class CustomersToActivateReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646643
*/
@Entity
class CustomersToActivateReadModelEntity {
  @Id @Column(name = "customerId") var customerId: String? = null

  @Column(name = "name") var name: String? = null

  @Column(name = "email") var email: String? = null
  @Column(name = "activated") var activated: Boolean = false
}

data class CustomersToActivateReadModel(val data: List<CustomersToActivateReadModelEntity>)
