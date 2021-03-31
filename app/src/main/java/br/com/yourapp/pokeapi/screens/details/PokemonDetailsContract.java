package br.com.yourapp.pokeapi.screens.details;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.yourapp.pokeapi.models.Abilities;
import br.com.yourapp.pokeapi.models.PokemonInfo;
import br.com.yourapp.pokeapi.screens.BasePresenter;
import br.com.yourapp.pokeapi.screens.BaseView;

public class PokemonDetailsContract {

    interface View extends BaseView<Presenter> { }

    interface Presenter extends BasePresenter {
        void loadPokemonAbilities(List<Abilities> abilities, RecyclerView abilitiesList, TextView txtLoadingAbilities);
        void loadPokemonTypes(PokemonInfo.Type type, RecyclerView typesList, TextView txtLoadingTypes);
    }
}
