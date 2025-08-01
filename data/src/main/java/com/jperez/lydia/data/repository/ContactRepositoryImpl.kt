package com.jperez.lydia.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jperez.lydia.data.model.ContactATO
import com.jperez.lydia.data.paging.ContactPagingSource
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl : ContactRepository {

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
}
