package com.jperez.lydia.feature.model

data class ContactDetailUI(
    val name : String,
    val imageUrl: String,
    val phone: String,
    val mail: String,
    val address: String,
    val city: String,
    val state: String,
    val country: String,
    val timezoneInfo : String,
    val birthInfo: String,
)