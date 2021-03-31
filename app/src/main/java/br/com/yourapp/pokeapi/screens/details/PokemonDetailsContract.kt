package br.com.yourapp.pokeapi.screens.details

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.yourapp.pokeapi.models.Abilities
import br.com.yourapp.pokeapi.models.PokemonInfo
import br.com.yourapp.pokeapi.screens.BasePresenter
import br.com.yourapp.pokeapi.screens.BaseView

class PokemonDetailsContract {

    internal interface View : BaseView<Presenter?>
    interface Presenter : BasePresenter {
        fun loadPokemonAbilities(abilities: List<Abilities?>?, abilitiesList: RecyclerView?, txtLoadingAbilities: TextView?)
        fun loadPokemonTypes(type: PokemonInfo.Type?, typesList: RecyclerView?, txtLoadingTypes: TextView?)
    }
}