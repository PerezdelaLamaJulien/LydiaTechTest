package com.jperez.lydia.data.di

import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.datasource.ContactContactRemoteDataSource
import com.jperez.lydia.data.datasource.ContactContactRemoteDataSourceImpl
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
import org.koin.dsl.module

var dataKoinModule = module {
    factory<ApiClient> {
        ApiClient()
    }
    factory<ContactContactRemoteDataSource> {
        ContactContactRemoteDataSourceImpl()
    }
    factory<ContactRepository> {
        ContactRepositoryImpl()
    }
}