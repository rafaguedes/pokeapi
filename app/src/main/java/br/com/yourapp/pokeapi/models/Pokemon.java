package br.com.yourapp.pokeapi.models;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import br.com.yourapp.pokeapi.di.api.PokeAPI;
import br.com.yourapp.pokeapi.di.component.DaggerPokeComponent;
import br.com.yourapp.pokeapi.di.modules.ContextModule;
import br.com.yourapp.pokeapi.interfaces.OnPokemonInfoLoad;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pokemon implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;

    public PokemonInfo pokemonInfo;

    public void loadPokemonInfo(Context context, OnPokemonInfoLoad onPokemonInfoLoad) {
        PokeAPI pokeAPI = DaggerPokeComponent.builder().contextModule(new ContextModule(context)).build().getService();
        Call<PokemonInfo> call = pokeAPI.getPokemonInfo(url);
        call.enqueue(new Callback<PokemonInfo>() {
            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                pokemonInfo = response.body();
                onPokemonInfoLoad.onLoad(pokemonInfo);
            }

            @Override
            public void onFailure(Call<PokemonInfo> call, Throwable throwable) {
                call.cancel();
            }
        });
    }
}
