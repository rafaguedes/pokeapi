package br.com.yourapp.pokeapi.screens.list;

import androidx.annotation.NonNull;

import br.com.yourapp.pokeapi.di.api.PokeAPI;
import br.com.yourapp.pokeapi.di.component.DaggerPokeComponent;
import br.com.yourapp.pokeapi.di.modules.ContextModule;
import br.com.yourapp.pokeapi.interfaces.OnInitialLoadCallback;
import br.com.yourapp.pokeapi.models.PokemonSearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dagger.internal.Preconditions.checkNotNull;

public class PokemonListPresenter implements PokemonListContract.Presenter {

    private final PokemonListContract.View galleryView;

    protected PokemonListPresenter(@NonNull PokemonListFragment pokemonListFragment) {
        this.galleryView = checkNotNull(pokemonListFragment, "galleryView cannot be null!");
        this.galleryView.setPresenter(this);
    }

    @Override
    public void initialLoad(OnInitialLoadCallback onInitialLoadCallBack) {
        PokeAPI pokeAPI = DaggerPokeComponent.builder().contextModule(new ContextModule(galleryView.context())).build().getService();
        Call<PokemonSearchResponse> call = pokeAPI.getPokemonsWithLimit(10,0);
        call.enqueue(new Callback<PokemonSearchResponse>() {
            @Override
            public void onResponse(Call<PokemonSearchResponse> call, Response<PokemonSearchResponse> response) {
                PokemonSearchResponse pokemonSearchResponse = response.body();
                /**
                 * We need to load the pokemon info to show the name and image
                 */
                if (pokemonSearchResponse != null) {
                    onInitialLoadCallBack.onLoad(pokemonSearchResponse.results);
                } else {
                    onInitialLoadCallBack.onError("An error occurred!");
                }
            }

            @Override
            public void onFailure(Call<PokemonSearchResponse> call, Throwable throwable) {
                onInitialLoadCallBack.onError("An error occurred!");
                call.cancel();
            }
        });
    }

}
