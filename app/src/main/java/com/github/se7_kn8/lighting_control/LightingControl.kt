package com.github.se7_kn8.lighting_control

import android.app.Application
import com.github.se7_kn8.lighting_control.repo.LightingControlRepository
import com.github.se7_kn8.lighting_control.service.LightingControlService
import com.github.se7_kn8.lighting_control.viewmodel.HomeViewModel
import com.github.se7_kn8.lighting_control.viewmodel.ModeViewModel
import com.github.se7_kn8.lighting_control.viewmodel.StaticColorViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

val viewModelModule = module {
    viewModel { StaticColorViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ModeViewModel(get()) }
}

val repoModule = module {
    single { LightingControlRepository(get()) }
}

val serviceModule = module {
    single {
        Retrofit.Builder().baseUrl(getProperty<String>("lighting_server_url")).addConverterFactory(ScalarsConverterFactory.create()).build()
            .create(LightingControlService::class.java)
    }
}

class LightingControl : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@LightingControl)
            androidFileProperties()

            modules(viewModelModule, repoModule, serviceModule)
        }

    }

}

