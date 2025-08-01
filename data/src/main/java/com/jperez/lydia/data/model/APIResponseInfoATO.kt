package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class APIResponseInfoATO(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)