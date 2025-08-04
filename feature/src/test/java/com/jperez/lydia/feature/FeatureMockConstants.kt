package com.jperez.lydia.feature

import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.model.ContactDetailUI
import com.jperez.lydia.feature.model.ContactListItemUI
import java.time.Instant

class FeatureMockConstants {
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

        val contactListItemUI = ContactListItemUI(
            name = "Marin Meunier",
            imageUrl = "https://randomuser.me/api/portraits/men/6.jpg",
            phone = "06-46-24-26-68",
            mail = "marin.meunier@example.com"
        )

        val contactDetailUI = ContactDetailUI(
            name = "Marin Meunier",
            imageUrl = "https://randomuser.me/api/portraits/men/6.jpg",
            phone = "06-46-24-26-68",
            mail = "marin.meunier@example.com",
            address = "815 Rue de la Gare",
            city = "Limoges - 53806",
            state = "Puy-de-Dôme",
            country = "France",
            timezoneInfo = "+01:00 - Europe/Paris",
            birthInfo = "16 juin 1949 - 76 ans"
        )
    }
}