package com.jperez.lydia.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jperez.lydia.data.datasource.ContactContactRemoteDataSource
import com.jperez.lydia.data.model.ContactATO
import org.koin.java.KoinJavaComponent.inject

/**
 * A [PagingSource] implementation for loading contacts from a remote data source.
 *
 * @param seed A seed value to generate a consistent set of contacts.
 */

class ContactPagingSource(private val seed: String): PagingSource<Int, ContactATO>() {
    private val remoteDataSource: ContactContactRemoteDataSource by inject(
        ContactContactRemoteDataSource::class.java)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContactATO> {
        return try {
            val currentPage = params.key ?: 1
            val response = remoteDataSource.getContacts(
                seed = seed,
                page = currentPage,
                pageSize = params.loadSize
            )
            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.results.isEmpty()) null else response.info.page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ContactATO>): Int? {
        return state.anchorPosition
    }

}