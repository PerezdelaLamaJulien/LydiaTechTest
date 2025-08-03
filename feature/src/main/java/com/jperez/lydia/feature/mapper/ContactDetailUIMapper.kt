package com.jperez.lydia.feature.mapper

import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.model.ContactDetailUI
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Mapper class to convert [Contact] to a [ContactDetailUI].
 */
class ContactDetailUIMapper {

    /**
     * Map a [Contact] object to a [ContactDetailUI] object.
     *
     * @param contact The [Contact] object to be mapped.
     * @return A [ContactDetailUI] object with the mapped values.
     */

    fun mapTo(contact: Contact): ContactDetailUI {
        val birthdayFormatted = contact.dateOfBirth.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
        return ContactDetailUI(
            name = "${contact.firstName} ${contact.lastName}",
            imageUrl = contact.largePicture,
            phone = contact.cell,
            mail = contact.email,
            address = contact.street,
            city = "${contact.city} - ${contact.postCode}",
            state = contact.state,
            country = contact.country,
            timezoneInfo = "${contact.timezoneOffset} - ${contact.timezoneDescription}",
            birthInfo = "$birthdayFormatted - ${contact.age} ans"
        )
    }
}