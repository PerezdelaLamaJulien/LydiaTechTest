package com.jperez.lydia.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jperez.lydia.data.datasource.ContactLocalDataSource
import com.jperez.lydia.data.model.ContactATO
import com.jperez.lydia.data.paging.ContactPagingSource
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.inject

class ContactRepositoryImpl : ContactRepository {

    private val contactLocalDataSource: ContactLocalDataSource by inject(ContactLocalDataSource::class.java)
    override suspend fun getContacts(seed: String): Flow<PagingData<ContactATO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 10
                ),
            pagingSourceFactory = {
                ContactPagingSource(seed)
            }
        ).flow
    }

    override suspend fun getSavedSeeds(): List<String> {
        return contactLocalDataSource.getsSavedSeeds()
    }

    override suspend fun deleteSavedSeed(seed: String) {
        return contactLocalDataSource.deleteSavedSeed(seed)
    }
}
