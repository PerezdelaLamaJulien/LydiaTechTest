package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactLocationStreetATO(
    val number: Int,
    val name: String,
)