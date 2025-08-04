package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactNameATO(
    val title: String,
    val first: String,
    val last: String,
)