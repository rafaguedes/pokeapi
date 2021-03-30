package br.com.yourapp.pokeapi.interfaces;

import br.com.yourapp.pokeapi.models.PokemonInfo;

public interface OnPokemonInfoLoad {
    void onLoad(PokemonInfo pokemonInfo);
}
