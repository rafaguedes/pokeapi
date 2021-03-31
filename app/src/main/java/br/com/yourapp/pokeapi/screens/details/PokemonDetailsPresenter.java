package br.com.yourapp.pokeapi.screens.details;

import androidx.annotation.NonNull;

import static dagger.internal.Preconditions.checkNotNull;

public class PokemonDetailsPresenter implements PokemonDetailsContract.Presenter {

    private final PokemonDetailsContract.View pokeViewDetailsView;

    protected PokemonDetailsPresenter(@NonNull PokemonDetailsFragment pokemonDetailsFragment) {
        this.pokeViewDetailsView = checkNotNull(pokemonDetailsFragment, "pokeViewDetailsView cannot be null!");
        this.pokeViewDetailsView.setPresenter(this);
    }
}
