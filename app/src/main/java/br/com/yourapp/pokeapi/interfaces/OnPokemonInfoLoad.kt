package br.com.yourapp.pokeapi.interfaces

import br.com.yourapp.pokeapi.models.PokemonInfo

interface OnPokemonInfoLoad {
    fun onLoad(pokemonInfo: PokemonInfo?)
}