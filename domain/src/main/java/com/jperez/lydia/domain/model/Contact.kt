package com.jperez.lydia.domain.model

import java.time.Instant

data class Contact(
    val id: String,
    val gender: String,
    val firstName : String,
    val lastName: String,
    val title: String,
    val city: String,
    val state: String,
    val country: String,
    val postCode: String,
    val street: String,
    val age : Int,
    val dateOfBirth: Instant,
    val dateOfRegistration: Instant,
    val email: String,
    val phone: String,
    val cell: String,
    val largePicture: String,
    val mediumPicture: String,
    val thumbPicture: String,
    )