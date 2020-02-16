package br.com.jaquelaurenti.pokemao.api

import br.com.jaquelaurenti.pokemao.model.HealthResponse
import br.com.jaquelaurenti.pokemao.model.Pokemon
import br.com.jaquelaurenti.pokemao.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface PokemonService {



    @GET("/api/pokemon/health")
    fun checkHealth() : Call<HealthResponse>

    @GET("/api/pokemon")
    fun getPokemons(
        @Query("sort") sort: String,
        @Query("size") size: Int
    ) : Call<PokemonResponse>


    @PUT("/api/pokemon")
    fun updatePokemon(
        @Body pokemon: Pokemon
    ) : Call<Pokemon>

    @GET("/api/pokemon/{number}")
    fun getPokemon(
        @Path("number") number: String
    ) : Call<Pokemon>

}