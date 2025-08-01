package com.jperez.lydia.feature.mapper

import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.feature.model.ListContactItemUI

/**
 * Mapper class to convert [Contact] to a [ListContactItemUI].
 */
class ListContactItemUIMapper {

    /**
     * Map a [Contact] object to a [ListContactItemUI] object.
     *
     * @param contact The [Contact] object to be mapped.
     * @return A [ListContactItemUI] object with the mapped values.
     */

    fun mapTo(contact: Contact): ListContactItemUI =
        ListContactItemUI(
            name = "${contact.firstName} ${contact.lastName}"
        )
}