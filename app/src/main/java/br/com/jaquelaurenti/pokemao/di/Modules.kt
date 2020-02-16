package br.com.jaquelaurenti.pokemao.di

import android.content.Context
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
import br.com.jaquelaurenti.pokemao.view.detail.DetailViewModel
import br.com.jaquelaurenti.pokemao.view.form.FormPokemonViewModel
import br.com.jaquelaurenti.pokemao.view.list.ListPokemonsViewModel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get()).create(PokemonService::class.java) }
    single { createPicassoAuth(get(), get()) }
}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ListPokemonsViewModel(get()) }
    viewModel { FormPokemonViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}


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

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}
