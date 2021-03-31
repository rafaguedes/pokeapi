package br.com.yourapp.pokeapi.screens.list;

import androidx.recyclerview.widget.GridLayoutManager;

import br.com.yourapp.pokeapi.interfaces.OnInitialLoadCallback;
import br.com.yourapp.pokeapi.interfaces.OnNextPageLoadCallback;
import br.com.yourapp.pokeapi.screens.BasePresenter;
import br.com.yourapp.pokeapi.screens.BaseView;

public class PokemonListContract {

    interface View extends BaseView<Presenter> {
        GridLayoutManager getLayoutManager();
    }

    interface Presenter extends BasePresenter {
        void initialLoad(OnInitialLoadCallback onInitialLoadCallBack);
        void loadNextPage(OnNextPageLoadCallback onNextPageLoadCallback);
    }
}
