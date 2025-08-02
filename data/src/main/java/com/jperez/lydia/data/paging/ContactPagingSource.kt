package com.jperez.lydia.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jperez.lydia.data.datasource.ContactLocalDataSource
import com.jperez.lydia.data.datasource.ContactRemoteDataSource
import com.jperez.lydia.data.model.ContactATO
import org.koin.java.KoinJavaComponent.inject

/**
 * A [PagingSource] implementation for loading contacts from a remote data source.
 *
 * @param seed A seed value to generate a consistent set of contacts.
 */

class ContactPagingSource(private val seed: String): PagingSource<Int, ContactATO>() {
    private val remoteDataSource: ContactRemoteDataSource by inject(
        ContactRemoteDataSource::class.java)
    private val localDataSource: ContactLocalDataSource by inject(
        ContactLocalDataSource::class.java)

    /**
     * Loads a page of contacts from the remote data source or local cache.
     * If the contacts are not available in the local cache,
     * it fetches them from the remote data source and saves them to the local cache.
     *
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContactATO> {
        return try {
            val currentPage = params.key ?: 1

            val cachedContacts = localDataSource.getRequestedContactFromDatabase(
                seed = seed,
                page = currentPage,
                pageSize = params.loadSize
            )
            if(cachedContacts != null){
                LoadResult.Page(
                    data = cachedContacts,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (cachedContacts.isEmpty()) null else currentPage + 1
                )
            } else {
                val response = remoteDataSource.getContacts(
                    seed = seed,
                    page = currentPage,
                    pageSize = params.loadSize
                )
                localDataSource.saveContactsToDatabase(
                    seed = seed,
                    page = currentPage,
                    pageSize = params.loadSize,
                    contacts = response.results
                )
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (response.results.isEmpty()) null else response.info.page + 1
                )
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ContactATO>): Int? {
        return state.anchorPosition
    }
}