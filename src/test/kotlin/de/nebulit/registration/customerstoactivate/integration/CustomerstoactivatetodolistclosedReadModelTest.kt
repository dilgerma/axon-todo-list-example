package de.nebulit.registration.customerstoactivate.integration

import de.nebulit.registration.common.support.BaseIntegrationTest
import de.nebulit.registration.common.support.RandomData
import de.nebulit.registration.common.support.awaitUntilAssserted
import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModel
import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModelQuery
import de.nebulit.registration.domain.commands.activatecustomer.ActivateCustomerCommand
import de.nebulit.registration.domain.commands.registercustomer.RegisterCustomerCommand
import java.util.*
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/** Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611877940 */
class CustomerstoactivatetodolistclosedReadModelTest : BaseIntegrationTest() {

  @Autowired private lateinit var commandGateway: CommandGateway

  @Autowired private lateinit var queryGateway: QueryGateway

  @Test
  fun `Customerstoactivatetodolistclosed Read Model Test`() {

    val customerId = UUID.randomUUID().toString()

    var registerCustomerCommand =
        RandomData.newInstance<RegisterCustomerCommand> { this.customerId = customerId }

    commandGateway.sendAndWait<Any>(registerCustomerCommand)

    var activateCustomerCommand =
        RandomData.newInstance<ActivateCustomerCommand> { this.customerId = customerId }

    commandGateway.sendAndWait<Any>(activateCustomerCommand)

    awaitUntilAssserted {
      var readModel =
          queryGateway.query(
              CustomersToActivateReadModelQuery(), CustomersToActivateReadModel::class.java)
      // TODO add assertions
      assertThat(readModel.get().data).isNotEmpty
    }
  }
}
