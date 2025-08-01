package com.jperez.lydia.data.di

import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
import org.koin.dsl.module

var dataKoinModule = module {
    factory<ApiClient> {
        ApiClient()
    }
    factory<ContactRepository> {
        ContactRepositoryImpl()
    }
}