package br.com.jaquelaurenti.pokemao

import br.com.jaquelaurenti.pokemao.di.viewModelModule
import android.app.Application
import br.com.jaquelaurenti.pokemao.di.networkModule
import br.com.jaquelaurenti.pokemao.di.repositoryModule
import com.facebook.stetho.Stetho
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidLogger
import org.koin.android.ext.koin.androidContext

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
// Start stetho
        Stetho.initializeWithDefaults(this) // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                    listOf( viewModelModule, networkModule, repositoryModule
                    ) )
        }
    } }
