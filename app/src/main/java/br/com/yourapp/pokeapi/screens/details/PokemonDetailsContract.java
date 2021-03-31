package br.com.yourapp.pokeapi.screens.details;

import br.com.yourapp.pokeapi.screens.BasePresenter;
import br.com.yourapp.pokeapi.screens.BaseView;

public class PokemonDetailsContract {

    interface View extends BaseView<Presenter> { }

    interface Presenter extends BasePresenter { }
}
