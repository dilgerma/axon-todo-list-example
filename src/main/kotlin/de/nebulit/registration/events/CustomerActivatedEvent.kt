package de.nebulit.registration.events

import de.nebulit.registration.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVIUZz1oE=/?moveToWidget=3458764624611715015
*/
data class CustomerActivatedEvent(var customerId: String) : Event
