package br.com.yourapp.pokeapi.interfaces;

import java.util.List;

import br.com.yourapp.pokeapi.models.Pokemon;

public interface OnInitialLoadCallback {
    void onLoad(List<Pokemon> pokemonList);
    void onError(String error);
}
