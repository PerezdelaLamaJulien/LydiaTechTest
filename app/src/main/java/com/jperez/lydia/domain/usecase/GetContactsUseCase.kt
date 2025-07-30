package com.jperez.lydia.domain.usecase

import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.domain.mapper.ContactMapper
import com.jperez.lydia.domain.model.Contact
import org.koin.java.KoinJavaComponent.inject

class GetContactsUseCase {
    private val repository: ContactRepository by inject(ContactRepository::class.java)
    private val mapper: ContactMapper by inject(ContactMapper::class.java)

    suspend fun getContacts(seed: String): List<Contact> {
        val contacts = repository.getContacts(seed)
        return contacts.map { contact ->
            mapper.mapTo(contact)
        }
    }
}