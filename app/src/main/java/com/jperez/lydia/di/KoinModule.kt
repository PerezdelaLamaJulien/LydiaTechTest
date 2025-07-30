package com.jperez.lydia.di

import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
import com.jperez.lydia.domain.mapper.ContactMapper
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.presentation.mapper.ListContactItemUIMapper
import com.jperez.lydia.presentation.viewmodel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var koinModule = module {
    factory<ContactMapper> {
        ContactMapper()
    }
    factory<ListContactItemUIMapper> {
        ListContactItemUIMapper()
    }
    factory<ApiClient> {
        ApiClient()
    }
    factory<ContactRepository> {
        ContactRepositoryImpl()
    }
    factory<GetContactsUseCase> {
        GetContactsUseCase()
    }

    viewModel<ListViewModel> {
        ListViewModel()
    }
}