package com.jperez.lydia.data

import com.jperez.lydia.data.model.APIResponseATO
import com.jperez.lydia.data.model.APIResponseInfoATO
import com.jperez.lydia.data.model.ContactATO
import com.jperez.lydia.data.model.ContactDateATO
import com.jperez.lydia.data.model.ContactIdATO
import com.jperez.lydia.data.model.ContactLocationATO
import com.jperez.lydia.data.model.ContactLocationCoordinatesATO
import com.jperez.lydia.data.model.ContactLocationStreetATO
import com.jperez.lydia.data.model.ContactLocationTimezoneATO
import com.jperez.lydia.data.model.ContactLoginATO
import com.jperez.lydia.data.model.ContactNameATO
import com.jperez.lydia.data.model.ContactPictureATO

class DataMockConstants {
    companion object {
        private val apiResponseInfoATO = APIResponseInfoATO(
            seed = "seed",
            page = 1,
            results = 0,
            version = "1.0",
        )

        private val contactATO = ContactATO(
            gender = "male",
            name = ContactNameATO(
                title = "Mr",
                first = "Marin",
                last = "Meunier"
            ),
            location = ContactLocationATO(
                street = ContactLocationStreetATO(
                    number = 815,
                    name = "Rue de la Gare"
                ),
                city = "Limoges",
                state = "Puy-de-Dôme",
                country = "France",
                postCode = 53806,
                coordinates = ContactLocationCoordinatesATO(
                    latitude = "-3.9384",
                    longitude = "-81.7879"
                ),
                timezone = ContactLocationTimezoneATO(
                    offset = "+9:00",
                    description = "Tokyo, Seoul, Osaka, Sapporo, Yakutsk"
                ),
            ),
            email = "marin.meunier@example.com",
            login = ContactLoginATO(
                uuid = "9ae1a7c7-77df-48cd-a0bb-e1b0c3527058",
                username = "sadpeacock902",
                password = "1223",
                salt = "EB17IVYy",
                md5 = "5acba14df336f8b6e542ac5e482c1b3e",
                sha1 = "53683ae46a7cea2d6c308d9ac5e47ebf36599898",
                sha256 = "c5511590d3dca7f324872c0d9e959bdd073c65989c46a88db5e9f6c70a63932f"
            ),
            dateOfBirth = ContactDateATO(
                date = "1949-06-16T15:54:25.494Z",
                age = 76
            ),
            registered = ContactDateATO(
                date = "2015-01-31T06:11:25.576Z",
                age = 10
            ),
            phone = "04-23-14-03-57",
            cell = "06-46-24-26-68",
            id = ContactIdATO(
                name = "INSEE",
                value = "1NNaN22049525 97"
            ),
            picture = ContactPictureATO(
                large = "https://randomuser.me/api/portraits/men/6.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/6.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/6.jpg"
            ),
            nat = "FR"
        )

        val apiResponseATO = APIResponseATO(
            results = listOf(contactATO),
            info = apiResponseInfoATO
        )
    }
}