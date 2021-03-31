package br.com.yourapp.pokeapi.screens.list

import androidx.recyclerview.widget.GridLayoutManager
import br.com.yourapp.pokeapi.interfaces.OnInitialLoadCallback
import br.com.yourapp.pokeapi.interfaces.OnNextPageLoadCallback
import br.com.yourapp.pokeapi.screens.BasePresenter
import br.com.yourapp.pokeapi.screens.BaseView

class PokemonListContract {
    interface View : BaseView<Presenter?> {
        fun getCurrentLayoutManager(): GridLayoutManager?
    }

    interface Presenter : BasePresenter {
        fun initialLoad(onInitialLoadCallBack: OnInitialLoadCallback?)
        fun loadNextPage(onNextPageLoadCallback: OnNextPageLoadCallback?)
    }
}