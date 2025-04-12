package de.nebulit.registration.events

import de.nebulit.registration.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611646274
*/
data class CustomerRegisteredEvent(var customerId: String, var name: String, var email: String) :
    Event
