package br.com.yourapp.pokeapi.screens.list

import br.com.yourapp.pokeapi.di.component.DaggerPokeComponent
import br.com.yourapp.pokeapi.di.modules.ContextModule
import br.com.yourapp.pokeapi.interfaces.OnInitialLoadCallback
import br.com.yourapp.pokeapi.interfaces.OnNextPageLoadCallback
import br.com.yourapp.pokeapi.models.PokemonSearchResponse
import dagger.internal.Preconditions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListPresenter(pokemonListFragment: PokemonListFragment) : PokemonListContract.Presenter {
    private val pokemonListView: PokemonListContract.View
    var pokemonSearchResponse: PokemonSearchResponse? = null

    init {
        pokemonListView = Preconditions.checkNotNull(pokemonListFragment, "pokemonListView cannot be null!")
        pokemonListView.setCurrentPresenter(this)
    }

    override fun initialLoad(onInitialLoadCallBack: OnInitialLoadCallback?) {
        val pokeAPI = DaggerPokeComponent.builder().contextModule(ContextModule(pokemonListView.context()!!)).build().service
        val call = pokeAPI?.getPokemonsWithLimit(10, 0)
        call?.enqueue(object : Callback<PokemonSearchResponse?> {
            override fun onResponse(call: Call<PokemonSearchResponse?>, response: Response<PokemonSearchResponse?>) {
                pokemonSearchResponse = response.body()
                /**
                 * We need to load the pokemon info to show the name and image
                 */
                if (pokemonSearchResponse != null) {
                    onInitialLoadCallBack?.onLoad(pokemonSearchResponse!!.results)
                } else {
                    onInitialLoadCallBack?.onError("An error occurred!")
                }
            }

            override fun onFailure(call: Call<PokemonSearchResponse?>, throwable: Throwable) {
                onInitialLoadCallBack?.onError("An error occurred!")
                call.cancel()
            }
        })
    }

    override fun loadNextPage(onNextPageLoadCallback: OnNextPageLoadCallback?) {
        val pokeAPI = DaggerPokeComponent.builder().contextModule(ContextModule(pokemonListView.context()!!)).build().service
        val call = pokeAPI?.getPokemonsWithUrl(pokemonSearchResponse!!.next)
        call?.enqueue(object : Callback<PokemonSearchResponse?> {
            override fun onResponse(call: Call<PokemonSearchResponse?>, response: Response<PokemonSearchResponse?>) {
                pokemonSearchResponse = response.body()
                /**
                 * We need to load the pokemon info to show the name and image
                 */
                if (pokemonSearchResponse != null) {
                    onNextPageLoadCallback?.onLoad(pokemonSearchResponse!!.results)
                } else {
                    onNextPageLoadCallback?.onError("An error occurred!")
                }
            }

            override fun onFailure(call: Call<PokemonSearchResponse?>, throwable: Throwable) {
                onNextPageLoadCallback?.onError("An error occurred!")
                call.cancel()
            }
        })
    }
}