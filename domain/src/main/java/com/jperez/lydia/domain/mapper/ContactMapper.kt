package com.jperez.lydia.domain.mapper

import com.jperez.lydia.data.model.ContactATO
import com.jperez.lydia.domain.model.Contact
import java.time.Instant

/**
 * Mapper class to convert [ContactATO] to [Contact].
 */
class ContactMapper {

    /**
     * Map a [ContactATO] object to a [Contact] object.
     *
     * @param ato The [ContactATO] object to be mapped.
     * @return A [Contact] object with the mapped values.
     */
    fun mapTo(ato: ContactATO): Contact =
        Contact(
            id = ato.login.uuid,
            gender = ato.gender,
            firstName = ato.name.first,
            lastName = ato.name.last,
            title = ato.name.title,
            city = ato.location.city,
            state = ato.location.state,
            country = ato.location.country,
            postCode = ato.location.postCode,
            street = "${ato.location.street.number} ${ato.location.street.name}",
            age = ato.dateOfBirth.age,
            dateOfBirth = Instant.parse(ato.dateOfBirth.date),
            dateOfRegistration = Instant.parse(ato.registered.date),
            email = ato.email,
            phone = ato.phone,
            cell = ato.cell,
            largePicture = ato.picture.large,
            mediumPicture = ato.picture.medium,
            thumbPicture = ato.picture.thumbnail,
            timezoneOffset = ato.location.timezone.offset,
            timezoneDescription = ato.location.timezone.description,
        )
}