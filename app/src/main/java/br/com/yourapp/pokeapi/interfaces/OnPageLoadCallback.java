package br.com.yourapp.pokeapi.interfaces;

import java.util.List;

import br.com.yourapp.pokeapi.models.Pokemon;

public interface OnPageLoadCallback {
    void onLoad(List<Pokemon> photoArrayList);
    void onError(String error);
}
