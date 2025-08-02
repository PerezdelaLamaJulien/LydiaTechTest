package com.jperez.lydia.data.di

import androidx.room.Room
import com.jperez.lydia.data.api.ApiClient
import com.jperez.lydia.data.database.AppDatabase
import com.jperez.lydia.data.database.ContactDao
import com.jperez.lydia.data.database.PaginationInfoDao
import com.jperez.lydia.data.datasource.ContactLocalDataSource
import com.jperez.lydia.data.datasource.ContactLocalDataSourceImpl
import com.jperez.lydia.data.datasource.ContactRemoteDataSource
import com.jperez.lydia.data.datasource.ContactRemoteDataSourceImpl
import com.jperez.lydia.data.mapper.ContactAtoEntityMapper
import com.jperez.lydia.data.repository.ContactRepository
import com.jperez.lydia.data.repository.ContactRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

var dataKoinModule = module {
    factory<ApiClient> {
        ApiClient()
    }
    factory<ContactRemoteDataSource> {
        ContactRemoteDataSourceImpl()
    }
    factory<ContactLocalDataSource> {
        ContactLocalDataSourceImpl()
    }
    factory<ContactRepository> {
        ContactRepositoryImpl()
    }
    factory<ContactAtoEntityMapper> {
        ContactAtoEntityMapper()
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "lydia_db"
        ).build()
    }
    single<PaginationInfoDao> {
        get<AppDatabase>().paginationInfoDao()
    }
    single<ContactDao> {
        get<AppDatabase>().contactDao()
    }
}