package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactPictureATO(
    val large: String,
    val medium: String,
    val thumbnail: String,
)