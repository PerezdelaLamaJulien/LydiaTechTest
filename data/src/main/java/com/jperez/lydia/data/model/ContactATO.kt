package com.jperez.lydia.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactATO(
    val gender: String,
    val name: ContactNameATO,
    val location: ContactLocationATO,
    val email: String,
    val login: ContactLoginATO,
    @SerialName("dob")
    val dateOfBirth: ContactDateATO,
    val registered: ContactDateATO,
    val phone: String,
    val cell: String,
    val id: ContactIdATO,
    val picture: ContactPictureATO,
    val nat: String,
)