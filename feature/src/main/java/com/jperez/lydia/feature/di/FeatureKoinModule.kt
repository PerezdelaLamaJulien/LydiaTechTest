package com.jperez.lydia.feature.di

import com.jperez.lydia.feature.mapper.ListContactItemUIMapper
import com.jperez.lydia.feature.viewmodel.ContactListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var featureKoinModule = module {
    factory<ListContactItemUIMapper> {
        ListContactItemUIMapper()
    }
    viewModel<ContactListViewModel> {
        ContactListViewModel()
    }
}