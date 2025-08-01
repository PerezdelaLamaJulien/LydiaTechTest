package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactLocationCoordinatesATO(
    val latitude: String,
    val longitude: String,
)