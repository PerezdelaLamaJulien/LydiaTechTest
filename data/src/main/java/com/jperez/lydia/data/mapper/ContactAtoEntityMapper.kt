package com.jperez.lydia.data.mapper

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
import com.jperez.lydia.data.model.entity.ContactEntity

class ContactAtoEntityMapper {

    /**
     * Map a list of [ContactATO] objects to a list of [ContactEntity] objects.
     *
     * @param atos The list of [ContactATO] objects to be mapped.
     * @param paginationInfoId The ID of the pagination info to associate with the contacts.
     * @return A list of [ContactEntity] objects with the mapped values.
     */
    fun mapATOListToEntityList(
        atos: List<ContactATO>,
        paginationInfoId: String
    ): List<ContactEntity> {
        return atos.map { ato ->
            mapATOToEntity(ato, paginationInfoId)
        }
    }

    /**
     * Map a list of [ContactEntity] objects to a list of [ContactATO] objects.
     *
     * @param entities The list of [ContactEntity] objects to be mapped.
     * @return A list of [ContactATO] objects with the mapped values.
     */
    fun mapEntityListToATOList(entities: List<ContactEntity>): List<ContactATO> {
        return entities.map { ato ->
            mapEntityToATO(ato)
        }
    }

    /**
     * Map a [ContactATO] object to a [ContactEntity] object.
     *
     * @param ato The [ContactATO] object to be mapped.
     * @param paginationInfoId The ID of the pagination info to associate with the contact.
     * @return A [ContactEntity] object with the mapped values.
     */
    private fun mapATOToEntity(ato: ContactATO, paginationInfoId: String): ContactEntity =
        ContactEntity(
            paginationInfo = paginationInfoId,
            uid = ato.login.uuid,
            gender = ato.gender,
            firstName = ato.name.first,
            lastName = ato.name.last,
            title = ato.name.title,
            city = ato.location.city,
            state = ato.location.state,
            country = ato.location.country,
            postCode = ato.location.postCode,
            streetName = ato.location.street.name,
            streetNumber = ato.location.street.number,
            age = ato.dateOfBirth.age,
            dateOfBirth = ato.dateOfBirth.date,
            dateOfRegistration = ato.registered.date,
            email = ato.email,
            phone = ato.phone,
            cell = ato.cell,
            largePicture = ato.picture.large,
            mediumPicture = ato.picture.medium,
            thumbPicture = ato.picture.thumbnail,
            coordinatesLatitude = ato.location.coordinates.latitude,
            coordinatesLongitude = ato.location.coordinates.longitude,
            timeZoneOffset = ato.location.timezone.offset,
            timeZoneDescription = ato.location.timezone.description,
            loginUsername = ato.login.username,
            loginPassword = ato.login.password,
            loginSalt = ato.login.salt,
            loginMd5 = ato.login.md5,
            loginSha1 = ato.login.sha1,
            loginSha256 = ato.login.sha256,
            registrationAge = ato.registered.age,
            contactName = ato.id.name,
            contactValue = ato.id.value,
            nationality = ato.nat
        )

    /**
     * Map a [ContactEntity] object to a [ContactATO] object.
     *
     * @param entity The [ContactEntity] object to be mapped.
     * @return A [ContactATO] object with the mapped values.
     */
    private fun mapEntityToATO(entity: ContactEntity): ContactATO {
        return ContactATO(
            gender = entity.gender,
            name = ContactNameATO(
                title = entity.title,
                first = entity.firstName,
                last = entity.lastName
            ),
            location = ContactLocationATO(
                street = ContactLocationStreetATO(
                    number = entity.streetNumber,
                    name = entity.streetName
                ),
                city = entity.city,
                state = entity.state,
                country = entity.country,
                postCode = entity.postCode,
                coordinates = ContactLocationCoordinatesATO(
                    latitude = entity.coordinatesLatitude,
                    longitude = entity.coordinatesLongitude
                ),
                timezone = ContactLocationTimezoneATO(
                    offset = entity.timeZoneOffset,
                    description = entity.timeZoneDescription
                ),
            ),
            email = entity.email,
            login = ContactLoginATO(
                uuid = entity.uid,
                username = entity.loginUsername,
                password = entity.loginPassword,
                salt = entity.loginSalt,
                md5 = entity.loginMd5,
                sha1 = entity.loginSha1,
                sha256 = entity.loginSha256
            ),
            dateOfBirth = ContactDateATO(
                date = entity.dateOfBirth,
                age = entity.age
            ),
            registered = ContactDateATO(
                date = entity.dateOfRegistration,
                age = entity.registrationAge
            ),
            phone = entity.phone,
            cell = entity.cell,
            id = ContactIdATO(
                name = entity.contactName,
                value = entity.contactValue
            ),
            picture = ContactPictureATO(
                large = entity.largePicture,
                medium = entity.mediumPicture,
                thumbnail = entity.thumbPicture
            ),
            nat = entity.nationality

        )
    }
}