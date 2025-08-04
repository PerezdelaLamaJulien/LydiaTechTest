package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactLocationTimezoneATO(
    val offset: String,
    val description: String,
)