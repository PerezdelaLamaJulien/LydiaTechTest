package com.jperez.lydia.feature.di

import com.jperez.lydia.feature.mapper.ContactDetailUIMapper
import com.jperez.lydia.feature.mapper.ContactListItemUIMapper
import com.jperez.lydia.feature.viewmodel.ContactListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var featureKoinModule = module {
    factory<ContactListItemUIMapper> {
        ContactListItemUIMapper()
    }
    factory<ContactDetailUIMapper> {
        ContactDetailUIMapper()
    }
    viewModel<ContactListViewModel> {
        ContactListViewModel()
    }
}