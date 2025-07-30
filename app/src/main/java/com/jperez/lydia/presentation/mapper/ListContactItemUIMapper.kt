package com.jperez.lydia.presentation.mapper

import com.jperez.lydia.domain.model.Contact
import com.jperez.lydia.presentation.model.ListContactItemUI

class ListContactItemUIMapper {
    fun mapTo(contact: Contact): ListContactItemUI =
        ListContactItemUI(
            name = "${contact.firstName} ${contact.lastName}"
        )
}