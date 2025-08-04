package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactIdATO(
    val name: String,
    val value: String?,
)