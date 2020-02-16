package br.com.jaquelaurenti.pokemao.repository

import br.com.jaquelaurenti.pokemao.model.Pokemon
interface PokemonRepository {
    fun checkHealth(
    onComplete:() -> Unit,
    onError: (Throwable?) -> Unit
    )
    fun getPokemons(
        size: Int,
        sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}