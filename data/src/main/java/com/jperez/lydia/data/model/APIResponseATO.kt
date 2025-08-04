package com.jperez.lydia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class APIResponseATO(
    val results: List<ContactATO>,
    val info: APIResponseInfoATO
)