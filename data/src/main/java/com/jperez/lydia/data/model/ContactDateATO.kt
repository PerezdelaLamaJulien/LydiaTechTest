package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactDateATO(
    val date: String,
    val age: Int,
)