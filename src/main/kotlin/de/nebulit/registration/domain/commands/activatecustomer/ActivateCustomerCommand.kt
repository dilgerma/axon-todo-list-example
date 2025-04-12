package de.nebulit.registration.domain.commands.activatecustomer

import de.nebulit.registration.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611715118
*/
data class ActivateCustomerCommand(@TargetAggregateIdentifier var customerId: String) : Command
