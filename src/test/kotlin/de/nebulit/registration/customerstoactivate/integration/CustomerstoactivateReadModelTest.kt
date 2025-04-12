package de.nebulit.registration.customerstoactivate.integration

import de.nebulit.registration.common.support.BaseIntegrationTest
import de.nebulit.registration.common.support.RandomData
import de.nebulit.registration.common.support.awaitUntilAssserted
import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModel
import de.nebulit.registration.customerstoactivate.CustomersToActivateReadModelQuery
import de.nebulit.registration.domain.commands.registercustomer.RegisterCustomerCommand
import java.util.*
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/** Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611792861 */
class CustomerstoactivateReadModelTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @Test
    fun `Customerstoactivate Read Model Test`() {

        val customerId = UUID.randomUUID().toString()

        var registerCustomerCommand =
            RandomData.newInstance<RegisterCustomerCommand> { this.customerId = customerId }

        commandGateway.sendAndWait<Any>(registerCustomerCommand)

        awaitUntilAssserted {
            var readModel =
                queryGateway.query(
                    CustomersToActivateReadModelQuery(), CustomersToActivateReadModel::class.java
                )
            // TODO add assertions
            assertThat(readModel.get().data).hasSize(1)
            assertThat(readModel.get().data[0].customerId).isEqualTo(customerId)
            assertThat(readModel.get().data[0].activated).isTrue()
        }
    }
}
