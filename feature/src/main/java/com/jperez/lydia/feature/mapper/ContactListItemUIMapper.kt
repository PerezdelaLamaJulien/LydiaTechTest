package com.jperez.lydia.feature.mapper

import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.model.ContactListItemUI

/**
 * Mapper class to convert [Contact] to a [ContactListItemUI].
 */
class ContactListItemUIMapper {

    /**
     * Map a [Contact] object to a [ContactListItemUI] object.
     *
     * @param contact The [Contact] object to be mapped.
     * @return A [ContactListItemUI] object with the mapped values.
     */

    fun mapTo(contact: Contact): ContactListItemUI =
        ContactListItemUI(
            name = "${contact.firstName} ${contact.lastName}",
            imageUrl = contact.largePicture,
            phone = contact.cell,
            mail = contact.email,
        )
}