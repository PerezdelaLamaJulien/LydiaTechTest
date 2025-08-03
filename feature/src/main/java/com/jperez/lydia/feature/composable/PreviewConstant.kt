package com.jperez.lydia.feature.composable

import com.jperez.lydia.domain.model.Contact
import java.time.Instant

class PreviewConstant {
    companion object {
        val contact = Contact(
            gender = "male",
            firstName = "Marin",
            lastName = "Meunier",
            title = "Mr",
            street = "815 Rue de la Gare",
            city = "Limoges",
            state = "Puy-de-Dôme",
            country = "France",
            postCode = "53806",
            email = "marin.meunier@example.com",
            id = "9ae1a7c7-77df-48cd-a0bb-e1b0c3527058",
            dateOfBirth = Instant.parse("1949-06-16T15:54:25.494Z"),
            age = 76,
            dateOfRegistration = Instant.parse("2015-01-31T06:11:25.576Z"),
            phone = "04-23-14-03-57",
            cell = "06-46-24-26-68",
            largePicture = "https://randomuser.me/api/portraits/men/6.jpg",
            mediumPicture = "https://randomuser.me/api/portraits/med/men/6.jpg",
            thumbPicture = "https://randomuser.me/api/portraits/thumb/men/6.jpg",
            timezoneOffset = "+01:00",
            timezoneDescription = "Europe/Paris",
        )
    }
}