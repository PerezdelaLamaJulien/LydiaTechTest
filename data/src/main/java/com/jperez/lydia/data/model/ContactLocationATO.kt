package com.jperez.lydia.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactLocationATO(
    val city: String,
    val state: String,
    val country: String,
    @SerialName("postcode")
    val postCode: Int,
    val street: ContactLocationStreetATO,
    val coordinates: ContactLocationCoordinatesATO,
    val timezone: ContactLocationTimezoneATO,
)