package com.jperez.lydia.domain.usecase

import com.jperez.lydia.data.repository.ContactRepository
import org.koin.java.KoinJavaComponent.inject

/**
 * Use case to retrieve a list of seeds saved in database.
 *
 * This use case interacts with the [ContactRepository] to fetch saved seeds
 *
 * @property repository The repository to fetch saved seeds.
 */
class GetSavedSeedUseCase {
    private val repository: ContactRepository by inject(ContactRepository::class.java)

    /**
     * Retrieves a list of saved seeds from the repository.
     *
     * @return A list of strings representing saved seeds.
     */

    suspend fun execute(): List<String> {
        return repository.getSavedSeeds()
    }
}