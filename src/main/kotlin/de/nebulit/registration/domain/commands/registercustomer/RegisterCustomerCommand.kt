package de.nebulit.registration.domain.commands.registercustomer

import de.nebulit.registration.common.Command

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646358
*/
data class RegisterCustomerCommand(var customerId: String, var name: String, var email: String) :
    Command
