package br.com.yourapp.pokeapi.screens.details;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.yourapp.pokeapi.di.api.PokeAPI;
import br.com.yourapp.pokeapi.di.component.DaggerPokeComponent;
import br.com.yourapp.pokeapi.di.modules.ContextModule;
import br.com.yourapp.pokeapi.models.Abilities;
import br.com.yourapp.pokeapi.models.AbilityDetails;
import br.com.yourapp.pokeapi.models.PokemonInfo;
import br.com.yourapp.pokeapi.screens.details.adapter.AbilityListAdapter;
import br.com.yourapp.pokeapi.screens.details.adapter.TypeListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dagger.internal.Preconditions.checkNotNull;

public class PokemonDetailsPresenter implements PokemonDetailsContract.Presenter {

    private static final String TAG = "PokemonDetailsPresenter";

    private final PokemonDetailsContract.View pokeViewDetailsView;

    protected PokemonDetailsPresenter(@NonNull PokemonDetailsFragment pokemonDetailsFragment) {
        this.pokeViewDetailsView = checkNotNull(pokemonDetailsFragment, "pokeViewDetailsView cannot be null!");
        this.pokeViewDetailsView.setPresenter(this);
    }

    @Override
    public void loadPokemonAbilities(List<Abilities> abilities, RecyclerView abilitiesList, TextView txtLoadingAbilities) {

        ArrayList<AbilityDetails> details = new ArrayList<>();
        RecyclerView.Adapter adapter = new AbilityListAdapter(details, pokeViewDetailsView.context());
        abilitiesList.setAdapter(adapter);

        for (Abilities ability: abilities) {
            PokeAPI pokeAPI = DaggerPokeComponent.builder().contextModule(new ContextModule(pokeViewDetailsView.context())).build().getService();
            Call<AbilityDetails> call = pokeAPI.getAbilityDetails(ability.ability.url);
            call.enqueue(new Callback<AbilityDetails>() {
                @Override
                public void onResponse(Call<AbilityDetails> call, Response<AbilityDetails> response) {
                    AbilityDetails abilityDetails = response.body();
                    if(abilityDetails != null) {
                        txtLoadingAbilities.setVisibility(View.GONE);
                        abilitiesList.setVisibility(View.VISIBLE);

                        AbilityListAdapter currentAdapter = (AbilityListAdapter) adapter;
                        currentAdapter.add(abilityDetails);
                    }
                }

                @Override
                public void onFailure(Call<AbilityDetails> call, Throwable t) {
                    Log.e(TAG, "Retrofit error");
                    call.cancel();
                }
            });
        }

        RecyclerView.LayoutManager layout = new LinearLayoutManager(pokeViewDetailsView.context(), LinearLayoutManager.VERTICAL, false);
        abilitiesList.setLayoutManager(layout);
    }

    @Override
    public void loadPokemonTypes(PokemonInfo.Type type, RecyclerView typesList, TextView txtLoadingTypes) {

        ArrayList<PokemonInfo.Type> typeArrayList = new ArrayList<>();
        RecyclerView.Adapter adapter = new TypeListAdapter(typeArrayList, pokeViewDetailsView.context());
        typesList.setAdapter(adapter);

        PokeAPI pokeAPI = DaggerPokeComponent.builder().contextModule(new ContextModule(pokeViewDetailsView.context())).build().getService();
        Call<PokemonInfo.Type> call = pokeAPI.getType(type.url);
        call.enqueue(new Callback<PokemonInfo.Type>() {
            @Override
            public void onResponse(Call<PokemonInfo.Type> call, Response<PokemonInfo.Type> response) {
                PokemonInfo.Type type = response.body();
                if(type != null) {
                    txtLoadingTypes.setVisibility(View.GONE);
                    typesList.setVisibility(View.VISIBLE);

                    TypeListAdapter currentAdapter = (TypeListAdapter) adapter;
                    currentAdapter.add(type);
                }
            }

            @Override
            public void onFailure(Call<PokemonInfo.Type> call, Throwable t) {
                Log.e(TAG, "Retrofit error");
                call.cancel();
            }
        });

        RecyclerView.LayoutManager layout = new LinearLayoutManager(pokeViewDetailsView.context(), LinearLayoutManager.VERTICAL, false);
        typesList.setLayoutManager(layout);
    }
}
