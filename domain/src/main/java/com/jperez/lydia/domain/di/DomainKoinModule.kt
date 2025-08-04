package com.jperez.lydia.domain.di

import com.jperez.lydia.domain.mapper.ContactMapper
import com.jperez.lydia.domain.usecase.DeleteSavedSeedUseCase
import com.jperez.lydia.domain.usecase.GetContactsUseCase
import com.jperez.lydia.domain.usecase.GetSavedSeedUseCase
import org.koin.dsl.module

var domainKoinModule = module {
    factory<ContactMapper> {
        ContactMapper()
    }
    factory<GetContactsUseCase> {
        GetContactsUseCase()
    }

    factory<GetSavedSeedUseCase> {
        GetSavedSeedUseCase()
    }

    factory<DeleteSavedSeedUseCase> {
        DeleteSavedSeedUseCase()
    }
}