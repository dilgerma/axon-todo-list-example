package de.nebulit.registration.readmodel

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class CustomersReadModelQuery(val customerId: String)

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646459
*/
@Entity
class CustomersReadModelEntity {
  @Id @Column(name = "customerId") var customerId: String? = null

  @Column(name = "name") var name: String? = null

  @Column(name = "email") var email: String? = null
}

data class CustomersReadModel(val data: CustomersReadModelEntity)
