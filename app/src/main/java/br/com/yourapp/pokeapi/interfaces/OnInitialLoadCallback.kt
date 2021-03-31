package br.com.yourapp.pokeapi.interfaces

import br.com.yourapp.pokeapi.models.Pokemon

interface OnInitialLoadCallback {
    fun onLoad(pokemonList: List<Pokemon?>?)
    fun onError(error: String?)
}