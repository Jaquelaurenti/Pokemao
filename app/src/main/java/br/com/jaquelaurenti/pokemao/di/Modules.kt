package br.com.jaquelaurenti.pokemao.di

import okhttp3.OkHttpClient
import okhttp3.Interceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import br.com.jaquelaurenti.pokemao.repository.PokemonRepository
import br.com.jaquelaurenti.pokemao.api.PokemonService
import br.com.jaquelaurenti.pokemao.repository.PokemonRepositoryImpl
import br.com.jaquelaurenti.pokemao.view.splash.SplashViewModel
import br.com.jaquelaurenti.pokemao.api.AuthInterceptor
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel


private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokedexdx.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .addNetworkInterceptor(StethoInterceptor())
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
return builder.build() }


val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}
val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}
val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createNetworkClient(get()).create(PokemonService::class.java) }
    single { createOkhttpClientAuth(get()) }
}
