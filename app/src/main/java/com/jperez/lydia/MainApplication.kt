package com.jperez.lydia

import android.app.Application
import com.jperez.lydia.data.di.dataKoinModule
import com.jperez.lydia.feature.di.featureKoinModule
import com.jperez.lydia.domain.di.domainKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(featureKoinModule, domainKoinModule, dataKoinModule)
        }
    }
}