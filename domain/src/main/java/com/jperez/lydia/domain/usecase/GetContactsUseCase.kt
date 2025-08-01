package com.jperez.lydia.domain.usecase

import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.domain.mapper.ContactMapper
import com.jperez.lydia.domain.model.Contact
import org.koin.java.KoinJavaComponent.inject

/**
 * Use case to retrieve a list of contacts.
 *
 * This use case interacts with the [ContactRepository] to fetch contacts
 * and maps them to the domain model using [ContactMapper].
 *
 * @property repository The repository to fetch contacts from.
 * @property mapper The mapper to convert data model to domain model.
 */
class GetContactsUseCase {
    private val repository: ContactRepository by inject(ContactRepository::class.java)
    private val mapper: ContactMapper by inject(ContactMapper::class.java)

    /**
     * Retrieves a list of contacts based on the provided seed.
     *
     * @param seed A seed value to generate a consistent set of contacts.
     * @return A list of [Contact] objects.
     */
    suspend fun getContacts(seed: String): List<Contact> {
        val contacts = repository.getContacts(seed)
        return contacts.map { contact ->
            mapper.mapTo(contact)
        }
    }
}