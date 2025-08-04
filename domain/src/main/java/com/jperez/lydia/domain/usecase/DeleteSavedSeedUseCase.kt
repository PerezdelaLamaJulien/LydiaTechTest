package com.jperez.lydia.domain.usecase

import com.jperez.lydia.data.repository.ContactRepository
import org.koin.java.KoinJavaComponent.inject

/**
 * Use case to delete saved seed from the database.
 *
 * This use case interacts with the [ContactRepository] to delete saved seed
 *
 * @property repository The repository to delete saved seed.
 */
class DeleteSavedSeedUseCase {
    private val repository: ContactRepository by inject(ContactRepository::class.java)

    /**
     * Delete saved seed from the database.
     *
     * @param seed The seed to be deleted from the saved seeds.
     */

    suspend fun execute(seed: String) {
        return repository.deleteSavedSeed(seed)
    }
}