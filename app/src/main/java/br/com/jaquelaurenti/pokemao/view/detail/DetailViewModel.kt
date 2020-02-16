package br.com.jaquelaurenti.pokemao.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jaquelaurenti.pokemao.model.Pokemon
import br.com.jaquelaurenti.pokemao.repository.PokemonRepository

class DetailViewModel(
    val pokemonRepository: PokemonRepository
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val pokemon = MutableLiveData<Pokemon>()
    fun getPokemon(number: String) {
        isLoading.value = true
        pokemonRepository.getPokemon(
            number,
            onComplete = {
                isLoading.value = false
                pokemon.value = it
            },
            onError = {
                isLoading.value = false
            }
        )
    }
}