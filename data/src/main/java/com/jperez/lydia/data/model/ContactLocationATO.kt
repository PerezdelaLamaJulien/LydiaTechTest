package com.jperez.lydia.data.model

import com.jperez.lydia.data.model.serializer.PostCodeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactLocationATO(
    val city: String,
    val state: String,
    val country: String,
    @SerialName("postcode")
    @Serializable(with = PostCodeSerializer::class)
    val postCode: String,
    val street: ContactLocationStreetATO,
    val coordinates: ContactLocationCoordinatesATO,
    val timezone: ContactLocationTimezoneATO,
)